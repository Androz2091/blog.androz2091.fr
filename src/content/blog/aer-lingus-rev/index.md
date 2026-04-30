---
title: 10h de vol pour casser l'appli Aer Lingus
date: 2026-04-29
lang: fr
featured: true
---

J'ai remarqué quelques minutes après le décollage de mon vol que la compagnie aérienne Aer Lingus avait développé une nouvelle appli mobile permettant de controller l'écran du siège à distance sur les vols transatlantiques.

Après un long débat avec moi-même pour savoir si le divertissement de pouvoir reverse engineer leur appli dans l'avion valaient les 20 euros de wifi, j'ai cracké, payé et téléchargé :
- Aer Lingus Play (l'appli à analyser)
- Frida (un outil pour patcher des APKs en live avec du JavaScript)
- Jadx (un décompilateur d'APKs)

Le résultat final est un CLI qui me permet de m'appairer avec le siège, puis d'envoyer n'importe quelle commande au siège (dont appeler le staff de l'avion). J'ai aussi découvert que cette appli est en fait juste un wrapper vendu par Panasonic a plusieurs compagnies aériennes :)

Le CLI :

<video src="/videos/aer-lingus-demo.mp4" controls
  width="100%"></video>
  
Ce qui s'affiche sur le siège :

<video src="/videos/aer-lingus-demo-screen.mp4" controls
  width="100%"></video>
  
```sh
poca@pocas-MacBook-Pro-2 aer-lingus-play-reverse-engineering % node seat-keyboard.js

    _    _____ ____      _     ___ _   _  ____ _   _ ____
   / \  | ____|  _ \    | |   |_ _| \ | |/ ___| | | / ___|
  / _ \ |  _| | |_) |   | |    | ||  \| | |  _| | | \___ \
 / ___ \| |___|  _ <    | |___ | || |\  | |_| | |_| |___) |
/_/   \_\_____|_| \_\   |_____|___|_| \_|\____|\___/|____/
             github.com/Androz2091/aer-lingus-play-reverse-engineering
             ────────────────────────────────────────────────────────────────

[+] minting AT: via /auth/v1/{challenge,solution}
[✓] AT acquired ([redacted]…)
passcode shown on seat ▸ [redacted]
[+] pairing: tap accept on the seat to confirm…
[✓] paired with seat 24D (Economy) — auth_token=[redacted]

  type text to auto-type. commands:
    /up /down /left /right /select    single key
    /home /back /enter
    /vol+ /vol-  /bri+ /bri-  /light
    /reset        re-zero tracked cursor to (0,0) — when seat cursor is on Q
    /origin       physically drive seat cursor to Q + re-zero
    /letter X     diagnostic: navigate to one char, ENTER, report position
    /unpair       clean up and exit
    /quit         exit without unpairing

seat 24D › /enter
  → { code: 2002, status: 'Message Accepted' }
seat 24D › /left
  → { code: 2002, status: 'Message Accepted' }
seat 24D › /enter
  → { code: 2002, status: 'Message Accepted' }
seat 24D › /enter
  → { code: 2002, status: 'Message Accepted' }
seat 24D › /origin
  cursor → (0,0) [sent 4 DOWN, 3 UP, 10 LEFT]
seat 24D › the smashing
  cursor now at (1,4)
seat 24D › 
```

## Setup Android

La première chose à faire est de setup le téléphone proprement, et de le connecter à mon ordi pour pouvoir analyser les requêtes sur un écran plus grand avec [Charles](https://www.charlesproxy.com/).

Problème : j'avais l'habitude de connecter mon téléphone au même routeur WiFi que mon ordi, puis de forcer mon téléphone à router le traffic des applis vers l'adresse locale de mon ordinateur (puis la requête partait de mon ordinateur vers Internet). mais... j'ai payé le WiFi sur mon téléphone, donc la requête finale à Internet **doit** partir de mon téléphone.

![network](./network.png)

Mon setup final :
- connecter mon tél au WiFi Aer Lingus
- connecter mon tél via USB à mon ordi
- créer un proxy avec ADB pour que `localhost-android:8888` arrive sur mon ordi `localhost-ordi:8888` (qui est en fait Charles)
- configurer le WiFi Aer Lingus dans Android pour utiliser le proxy `127.0.0.1:8888` (mon ordi)
- créer un hotspot WiFi Android auquel je connecte sur mon ordi

Comme le proxy n'est qu'au niveau des applis, les requêtes qui viennent des appareils connectés via hotspot ne sont traitées qu'à un niveau plus bas (IP) et sont envoyées directement sur l'interface WiFi Aer Lingus sans proxy ! Donc en résumé, pour une requête lambda :
- elle part de mon téléphone
- passe par USB vers mon ordi
- est interceptée et lue par Charles
- est renvoyée de mon ordi vers mon téléphone par WiFi
- puis envoyée directement au WiFi Aer Lingus !

Il nous reste quelques petites choses à setup après ça :
- télécharger le certificat via `http://chls.pro/ssl`
- installer [MoveCertificate](https://github.com/ys1231/MoveCertificate/releases) pour forcer les applis à trust le certificat (de user cert vers root cert)

mais... malgré ça, les requêtes sont bloquées car l'appli Panasonic ne trust pas le certificat SSL de Charles, même avec MoveCertificate. La solution, patcher l'appli avec Frida ([ssl-bypass.js](/aer-lingus-rev/ssl-bypass.js)).

```
poca@pocas-MacBook-Pro-2 platform-tools %  uvx --offline --from frida-tools --with frida==17.9.1 frida -U -f com.aerlingus.ife.companion \
    -l /Users/poca/Documents/platform-tools/ssl-bypass.js \
    -l /Users/poca/Documents/Github/aer-lingus-play-reverse-engineering/dump-airline-key.js
     ____
    / _  |   Frida 17.9.1 - A world-class dynamic instrumentation toolkit
   | (_| |
    > _  |   Commands:
   /_/ |_|       help      -> Displays the help system
   . . . .       object?   -> Display information about 'object'
   . . . .       exit/quit -> Exit
   . . . .
   . . . .   More info at https://frida.re/docs/home/
   . . . .
   . . . .   Connected to Pixel 9 Pro XL (id=[redacted])
Spawned `com.aerlingus.ife.companion`. Resuming main thread!
[Pixel 9 Pro XL::com.aerlingus.ife.companion ]-> [+] SSL bypass loaded
[+] SSLContext.init() bypass
```

Ok! On voit que l'appli fait une première requête vers les serveurs de Panasonic pour obtenir un challenge et envoyer une solution.

![Charles Init](./charles-init.png)

Requête pour l'endpoint `/challenge`
```
curl -H "AI: [redacted]" -H "User-Agent: Dalvik/2.1.0 (Linux; U; Android 16; Pixel 9 Pro XL Build/CP1A.260405.005)" -H "Host: secure.inflightpanasonic.aero" --data "" --compressed "https://secure.inflightpanasonic.aero/inflight/services/auth/v1/challenge"
```

et voilà la réponse reçue de la part du serveur:
```
{
	"counter": [redacted],
	"challenge": "[redacted]"
}
```

puis l'endpoint solution :
```
curl -H "User-Agent: Dalvik/2.1.0 (Linux; U; Android 16; Pixel 9 Pro XL Build/CP1A.260405.005)" -H "Host: secure.inflightpanasonic.aero" --data "{\"airline_id\":\"[redacted]\",\"counter\":[redacted],\"solution\":\"[redacted]\"}" --compressed "https://secure.inflightpanasonic.aero/inflight/services/auth/v1/solution"
{"airline_id":"[redacted]","counter":[redacted],"solution":"[redacted]
```
et la réponse finale :
```
{
	"counter": [redacted],
	"_t": "[redacted]",
	"_e": 3600
}
```

L'endpoint `/solution` renvoie ce token `_t` qui est ré-utilisé par toutes les requêtes suivantes. Malheureusement je ne vois pas de moyen simple de comprendre comment la solution est calculée à partir du challenge, donc nous allons devoir décompiler l'APK via Jadx:

`./jadx/bin/jadx --no-res --no-imports -d jadx-out "Aer+Lingus+Play_1.0.37_apkcombo.com.apk"`

Puis chercher `/solution`, pour tomber sur [`HttpAuthConnection.java`](/aer-lingus-rev/HttpAuthConnection.java)

L'appli stocke donc une `airline_key` quelque part. Elle est passée à `setAirlineKey(context, str)` au démarrage, et après une transformation un peu pénible (`setIsSeatBack`, ligne 331) elle est séparée en deux parties de 32 octets :

- la première moitié, c'est le `airline_id`, `[redacted]` dans le cas d'Aer Lingus, qui est envoyé en clair dans les headers du `/challenge`
- la deuxième, c'est la clé HMAC-SHA256, 32 octets de secret, jamais envoyés sur le réseau

Le code prend les 64 premiers caractères de la `airline_key` et leur applique deux passes :

- lignes 340 à 350 : un swap entre les indices impairs du début et les indices pairs de la fin (`bytes[1]` est échange avec `bytes[62]`, `bytes[3]` avec `bytes[60]`, etc.)
- lignes 351 à 369 : un reverse classique de la deuxième moitié (bytes 32 à 63)

Puis ce résultat est séparé en deux moitiés de 32 octets (le `airline_id` et le secret HMAC), et un MD5 du buffer entier est comparé aux 32 derniers caractères de la `airline_key` (qui sont là uniquement comme checksum, pour valider qu'on a bien retrouver une clé correcte). Pénible à lire, facile à reproduire grâce Jadx (et Claude Code qui sait passer sans problème de Java à JavaScript :).

Donc :
`solution = base64(hex(HMAC-SHA256(challenge, secret)))`. Laa concaténation `{"airline_id":...,"counter":N+1,"solution":"..."}` fait exactement 172 octets (le `Content-Length` dans Charles).
  
Reste à récupérer la `airline_key`. Comme `setAirlineKey` est appelée au démarrage de l'appli, on peut créer un hook sur la méthode avec Frida pour récupérer son premier argument :

```js
Java.perform(() => {
  const Cls = Java.use('aero.panasonic.inflight.services.appauth.HttpAuthConnection');
  Cls.setAirlineKey.implementation = function (ctx, str) {
    console.log('[AIRLINE_KEY]', str);
    return this.setAirlineKey(ctx, str);
  };
});
```

Je relance l'appli avec ce script en plus du SSL bypass et... nice:
```
[AIRLINE_KEY] [redacted]
```

Par curiosité je grep cette valeur dans les fichiers Java décompilés juste pour vérifier d'où elle vient :

```
$ grep -r '[redacted]' jadx-out/
jadx-out/sources/com/aerlingus/ife/companion/EINApplication.java:280:
  .appId("[redacted]")
```

lol 🤡  
La clé est juste hardcodée dans `EINApplication.java`, et c'est la même pour tous les utilisateurs d'Aer Lingus.

----

À partir de ce moment, il ne me reste plus qu'à identifier tous les payloads possibles puis de les mettre dans mon script de CLI pour pouvoir les automatiser, car une fois qu'on a le token d'authentification, les requêtes sont très simples.

Pour vraiment contrôler un siège il faut s'appairer :
- cliquer sur l'écran du siège pour obtenir le code
- le rentrer dans l'appli, qui fait un POST `/ped_pair` avec ce passcode + le token `_t`
- le serveur fait apparaître un dialog "Accept ?" sur le siège
- puis le serveur renvoie ça à l'appli :

![auth.png](./auth.png)

En cliquant sur les boutons de contrôle à distance dans l'appli, on voit que les requêtes suivantes sont envoyées avec ce même token !

![Charles Remote](./remote-cmd.png)

Maintenant que je peux envoyer des commandes comme "entrer", "gauche", "droite", etc. il me reste un dernier petit + à ajouter à mon CLI : le support du clavier. Pour ça, je dois ajouter à mon script la notion de curseur (pour qu'il se souvienne de sa position, et puisse calculer le chemin d'une touche à l'autre relativement par une suite de signaux "haut"/"bas"/"gauche"/"droite" à envoyer au serveur).

Petit problème, le clavier QWERTY du siège n'est pas une grille uniforme : Shift et Enter font 1.5 cellules de large, Space en fait 5, et la rangée du bas n'a que 5 touches au lieu de 10. Après quelques itérations (mon premier essai pour "the smashing" a tapé "puojnvnkl" mdr), ça finit par marcher ! J'ai quand même passé quasiment autant de temps là-dessus que sur tout le reste du rev.

----

Bilan : les 20e de wifi pour reverse engineer cette appli pendant 10h valaient le coup et l'appli de Panasonic est assez bien faite ! Ils ont des choses un peu bizarre qui font penser à de la sécurité par obfuscation (typiquement l'endpoint `challenge` pour obtenir un token qui lit simplement une clef hardcodée et fait une suite de manip bizarres dessus), mais quand même suffisamment sécurisée pour qu'il n'y ait pas vraiment beaucoup plus fun à faire que de repliquer leur appli dans un CLI. Se connecter à un siège random n'est pas possible si le passager n'a pas déjà appairé son appareil, et essayer d'obtenir son code d'appairage demanderait de tester environ 20M de possibilités !
