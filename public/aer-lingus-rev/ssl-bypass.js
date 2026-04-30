Java.perform(function () {
  console.log("[+] SSL bypass loaded")

  try {
    var X509TrustManager = Java.use("javax.net.ssl.X509TrustManager")
    var SSLContext = Java.use("javax.net.ssl.SSLContext")

    var TrustManager = Java.registerClass({
      name: "com.frida.TrustAllManager",
      implements: [X509TrustManager],
      methods: {
        checkClientTrusted: function (chain, authType) {},
        checkServerTrusted: function (chain, authType) {},
        getAcceptedIssuers: function () {
          return []
        },
      },
    })

    var TrustManagers = [TrustManager.$new()]

    var SSLContext_init = SSLContext.init.overload(
      "[Ljavax.net.ssl.KeyManager;",
      "[Ljavax.net.ssl.TrustManager;",
      "java.security.SecureRandom",
    )

    SSLContext_init.implementation = function (km, tm, sr) {
      console.log("[+] SSLContext.init() bypass")
      SSLContext_init.call(this, km, TrustManagers, sr)
    }
  } catch (e) {
    console.log("[-] SSLContext hook failed: " + e)
  }

  try {
    var TrustManagerImpl = Java.use(
      "com.android.org.conscrypt.TrustManagerImpl",
    )

    TrustManagerImpl.verifyChain.implementation = function (
      untrustedChain,
      trustAnchorChain,
      host,
      clientAuth,
      ocspData,
      tlsSctData,
    ) {
      console.log("[+] Conscrypt verifyChain bypass: " + host)
      return untrustedChain
    }
  } catch (e) {
    console.log("[-] Conscrypt verifyChain hook failed: " + e)
  }

  try {
    var CertificatePinner = Java.use("okhttp3.CertificatePinner")

    CertificatePinner.check.overload(
      "java.lang.String",
      "java.util.List",
    ).implementation = function (hostname, peerCertificates) {
      console.log(
        "[+] OkHttp CertificatePinner.check(List) bypass: " + hostname,
      )
      return
    }

    CertificatePinner.check.overload(
      "java.lang.String",
      "[Ljava.security.cert.Certificate;",
    ).implementation = function (hostname, peerCertificates) {
      console.log(
        "[+] OkHttp CertificatePinner.check(Cert[]) bypass: " + hostname,
      )
      return
    }
  } catch (e) {
    console.log("[-] OkHttp CertificatePinner hooks not found: " + e)
  }
})
