package aero.panasonic.inflight.services.appauth;

/* JADX INFO: loaded from: classes.dex */
public class HttpAuthConnection implements android.content.ServiceConnection {
    public static final java.lang.String AT = "AT";
    private static final java.util.Set<java.lang.String> AdLoggerRequestConstants;
    private static aero.panasonic.inflight.services.appauth.HttpAuthConnection LogAdRequest = null;
    private static long getContentType = 0;
    private static int getCrewEventsById = 1;
    private static int getCrewEventsId;
    private static final java.lang.String onAdLogged;
    private java.lang.String AdvertisementItem;
    private javax.net.ssl.SSLContext AdvertisementLogger$Listener;
    private java.lang.ref.WeakReference<android.content.Context> getAdvertisementId;
    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken getDestinationName;
    private java.lang.String getElapsedDuration;
    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken getEventType;
    private byte[] getLanguage;
    private aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi getZonePath;
    private java.lang.String mUserName;
    private byte[] setElapsedDuration;
    private long setEventType;
    private boolean logImpression = true;
    private boolean setLanguage = true;
    private boolean mGroundMode = false;
    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken AdvertisementLogger = new aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken("", 31536000);
    private final aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceEventCallback.Stub RequestBase = new aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceEventCallback.Stub() { // from class: aero.panasonic.inflight.services.appauth.HttpAuthConnection.1
        @Override // aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceEventCallback
        public void onIfeServiceEvent(int i, java.lang.String str) throws android.os.RemoteException {
        }

        @Override // aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceEventCallback
        public void onConnectionStateChange(int i, java.lang.String str) throws android.os.RemoteException {
            java.lang.String strAccess$000 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.access$000();
            java.lang.StringBuilder sb = new java.lang.StringBuilder("onConnectionStateChange() status=");
            sb.append(i);
            sb.append(", msg=");
            sb.append(str);
            aero.panasonic.inflight.services.utils.Log.v(strAccess$000, sb.toString());
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.setIsSeatBack(aero.panasonic.inflight.services.appauth.HttpAuthConnection.this);
            if (aero.panasonic.inflight.services.appauth.HttpAuthConnection.AnonymousClass4.setContentType[aero.panasonic.inflight.services.ifeservice.IfeService.IfeServiceEvent.values()[i].ordinal()] != 1) {
                return;
            }
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.isUiRunningTest(aero.panasonic.inflight.services.appauth.HttpAuthConnection.this);
        }
    };
    private int getDestinationType = hashCode();
    private java.lang.Boolean AdLoggerController$RequestHandler$MessageId = null;

    static void setChecksum() {
        getContentType = 8471653587772973099L;
    }

    static /* synthetic */ java.lang.String access$000() {
        int i = getCrewEventsId + 23;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        java.lang.String str = onAdLogged;
        int i3 = getCrewEventsId + 125;
        getCrewEventsById = i3 % 128;
        int i4 = i3 % 2;
        return str;
    }

    static /* synthetic */ void isUiRunningTest(aero.panasonic.inflight.services.appauth.HttpAuthConnection httpAuthConnection) {
        int i = getCrewEventsById + 105;
        getCrewEventsId = i % 128;
        if (i % 2 == 0) {
            httpAuthConnection.onConnectionReset();
            return;
        }
        httpAuthConnection.onConnectionReset();
        java.lang.Object[] objArr = null;
        int length = objArr.length;
    }

    static /* synthetic */ boolean setIsSeatBack(aero.panasonic.inflight.services.appauth.HttpAuthConnection httpAuthConnection) {
        int i = getCrewEventsById + 117;
        getCrewEventsId = i % 128;
        boolean z = false;
        if (!(i % 2 == 0)) {
            httpAuthConnection.logImpression = false;
        } else {
            httpAuthConnection.logImpression = true;
            z = true;
        }
        int i2 = getCrewEventsById + 25;
        getCrewEventsId = i2 % 128;
        int i3 = i2 % 2;
        return z;
    }

    static {
        setChecksum();
        onAdLogged = aero.panasonic.inflight.services.appauth.HttpAuthConnection.class.getSimpleName();
        java.util.HashSet hashSet = new java.util.HashSet();
        AdLoggerRequestConstants = hashSet;
        hashSet.add("https");
        int i = getCrewEventsById + 13;
        getCrewEventsId = i % 128;
        int i2 = i % 2;
    }

    /* JADX INFO: renamed from: aero.panasonic.inflight.services.appauth.HttpAuthConnection$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] setContentType = new int[aero.panasonic.inflight.services.ifeservice.IfeService.IfeServiceEvent.values().length];

        static {
            try {
                setContentType[aero.panasonic.inflight.services.ifeservice.IfeService.IfeServiceEvent.RECONNECTION.ordinal()] = 1;
            } catch (java.lang.NoSuchFieldError unused) {
            }
        }
    }

    private HttpAuthConnection() {
        javax.net.ssl.TrustManagerFactory trustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getTrustManagerFactory();
        try {
            this.AdvertisementLogger$Listener = javax.net.ssl.SSLContext.getInstance(buildDataBundle("ⁿ䉀\ue436", android.view.View.resolveSizeAndState(0, 0, 0) + 25127).intern());
            this.AdvertisementLogger$Listener.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (java.security.KeyManagementException | java.security.NoSuchAlgorithmException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0035 A[Catch: all -> 0x0058, TryCatch #2 {, blocks: (B:4:0x0003, B:10:0x0019, B:19:0x003c, B:28:0x0055, B:18:0x0035, B:15:0x0029, B:16:0x002a, B:24:0x004f, B:11:0x0022), top: B:38:0x0003, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0056 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized aero.panasonic.inflight.services.appauth.HttpAuthConnection getInstance() {
        /*
            java.lang.Class<aero.panasonic.inflight.services.appauth.HttpAuthConnection> r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.class
            monitor-enter(r0)
            int r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById     // Catch: java.lang.Throwable -> L58
            int r1 = r1 + 101
            int r2 = r1 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r2     // Catch: java.lang.Throwable -> L58
            int r1 = r1 % 2
            r2 = 52
            if (r1 == 0) goto L14
            r1 = 39
            goto L16
        L14:
            r1 = 52
        L16:
            r3 = 0
            if (r1 == r2) goto L2a
            java.lang.String r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged     // Catch: java.lang.Throwable -> L58
            java.lang.String r2 = "getInstance()"
            aero.panasonic.inflight.services.utils.Log.v(r1, r2)     // Catch: java.lang.Throwable -> L58
            aero.panasonic.inflight.services.appauth.HttpAuthConnection r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.LogAdRequest     // Catch: java.lang.Throwable -> L58
            super.hashCode()     // Catch: java.lang.Throwable -> L28
            if (r1 != 0) goto L3c
            goto L35
        L28:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L58
        L2a:
            java.lang.String r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged     // Catch: java.lang.Throwable -> L58
            java.lang.String r2 = "getInstance()"
            aero.panasonic.inflight.services.utils.Log.v(r1, r2)     // Catch: java.lang.Throwable -> L58
            aero.panasonic.inflight.services.appauth.HttpAuthConnection r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.LogAdRequest     // Catch: java.lang.Throwable -> L58
            if (r1 != 0) goto L3c
        L35:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection r1 = new aero.panasonic.inflight.services.appauth.HttpAuthConnection     // Catch: java.lang.Throwable -> L58
            r1.<init>()     // Catch: java.lang.Throwable -> L58
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.LogAdRequest = r1     // Catch: java.lang.Throwable -> L58
        L3c:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.LogAdRequest     // Catch: java.lang.Throwable -> L58
            int r2 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId     // Catch: java.lang.Throwable -> L58
            int r2 = r2 + 47
            int r4 = r2 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r4     // Catch: java.lang.Throwable -> L58
            int r2 = r2 % 2
            if (r2 != 0) goto L4c
            r2 = 1
            goto L4d
        L4c:
            r2 = 0
        L4d:
            if (r2 == 0) goto L56
            super.hashCode()     // Catch: java.lang.Throwable -> L54
            monitor-exit(r0)
            return r1
        L54:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L58
        L56:
            monitor-exit(r0)
            return r1
        L58:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.getInstance():aero.panasonic.inflight.services.appauth.HttpAuthConnection");
    }

    public static boolean checkUrl(java.lang.String str) {
        try {
            boolean zCheckUrl = checkUrl(new java.net.URL(str));
            int i = getCrewEventsId + 63;
            getCrewEventsById = i % 128;
            int i2 = i % 2;
            return zCheckUrl;
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            aero.panasonic.inflight.services.utils.Log.e(onAdLogged, "url is not correct: ".concat(java.lang.String.valueOf(str)));
            return false;
        }
    }

    @android.annotation.SuppressLint({"DefaultLocale"})
    public static boolean checkUrl(java.net.URL url) {
        int i = getCrewEventsById + 125;
        getCrewEventsId = i % 128;
        if ((i % 2 != 0 ? ' ' : '6') != '6') {
            java.lang.String lowerCase = url.getProtocol().toLowerCase();
            url.getHost();
            boolean zContains = AdLoggerRequestConstants.contains(lowerCase);
            int i2 = 88 / 0;
            return zContains;
        }
        java.lang.String lowerCase2 = url.getProtocol().toLowerCase();
        url.getHost();
        return AdLoggerRequestConstants.contains(lowerCase2);
    }

    private synchronized void isSeatBack(android.content.Context context) {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "bindToIfeService");
        this.AdLoggerController$RequestHandler$MessageId = java.lang.Boolean.FALSE;
        new aero.panasonic.inflight.services.utils.ServiceUtil(context).bindToIfeService(this, 73);
        this.getAdvertisementId = new java.lang.ref.WeakReference<>(context);
        int i = getCrewEventsById + 35;
        getCrewEventsId = i % 128;
        if (i % 2 != 0) {
            java.lang.Object obj = null;
            super.hashCode();
        }
    }

    private void setFileOffset() {
        if (this.getAdvertisementId != null) {
            int i = getCrewEventsById + 67;
            getCrewEventsId = i % 128;
            int i2 = i % 2;
            if (this.getAdvertisementId.get() != null) {
                int i3 = getCrewEventsById + 79;
                getCrewEventsId = i3 % 128;
                int i4 = i3 % 2;
                this.getAdvertisementId.get().unbindService(this);
                int i5 = getCrewEventsById + 79;
                getCrewEventsId = i5 % 128;
                int i6 = i5 % 2;
            }
        }
        this.getAdvertisementId = null;
    }

    protected void finalize() throws java.lang.Throwable {
        int i = getCrewEventsId + 55;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        super.finalize();
        setFileOffset();
        int i3 = getCrewEventsId + 89;
        getCrewEventsById = i3 % 128;
        int i4 = i3 % 2;
    }

    public void init(android.content.Context context) {
        int i = getCrewEventsId + 123;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        if (context == null) {
            throw new java.lang.NullPointerException("context cannot be null.");
        }
        if (this.AdLoggerController$RequestHandler$MessageId == null) {
            int i3 = getCrewEventsById + 21;
            getCrewEventsId = i3 % 128;
            if (i3 % 2 != 0) {
                isSeatBack(context.getApplicationContext());
                java.lang.Object[] objArr = null;
                int length = objArr.length;
            } else {
                isSeatBack(context.getApplicationContext());
            }
            int i4 = getCrewEventsId + 99;
            getCrewEventsById = i4 % 128;
            if (i4 % 2 == 0) {
            }
        }
    }

    public void setAirlineKey(android.content.Context context, java.lang.String str) {
        java.lang.String isSeatBack2;
        int i = getCrewEventsId + 47;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "setAirlineKey()");
        init(context);
        try {
            isSeatBack2 = setIsSeatBack(str);
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            aero.panasonic.inflight.services.utils.Log.e(onAdLogged, e.getLocalizedMessage());
            isSeatBack2 = "";
        }
        if ((isSeatBack2 != null ? 'Y' : (char) 7) == 'Y') {
            int i3 = getCrewEventsById + 113;
            getCrewEventsId = i3 % 128;
            int i4 = i3 % 2;
            if (!(isSeatBack2.isEmpty())) {
                int i5 = getCrewEventsById + 101;
                getCrewEventsId = i5 % 128;
                int i6 = i5 % 2;
                setAirlineId(isSeatBack2);
                int i7 = getCrewEventsId + 1;
                getCrewEventsById = i7 % 128;
                int i8 = i7 % 2;
                return;
            }
        }
        this.getElapsedDuration = "";
    }

    private java.lang.String setIsSeatBack(java.lang.String str) throws java.security.NoSuchAlgorithmException {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "extractAirlineId() ".concat(java.lang.String.valueOf(str)));
        try {
            byte[] bytes = str.substring(0, 64).getBytes();
            int i = getCrewEventsId + 1;
            getCrewEventsById = i % 128;
            int i2 = i % 2;
            java.lang.String str2 = new java.lang.String(str.substring(64).getBytes());
            int length = bytes.length - 2;
            int i3 = 1;
            while (true) {
                if ((i3 < length ? (char) 5 : '2') != 5) {
                    break;
                }
                byte b2 = bytes[i3];
                bytes[i3] = bytes[length];
                bytes[length] = b2;
                i3 += 2;
                length -= 2;
            }
            int length2 = bytes.length / 2;
            int length3 = bytes.length - 1;
            while (length2 < length3) {
                int i4 = getCrewEventsId + 97;
                getCrewEventsById = i4 % 128;
                if ((i4 % 2 == 0 ? org.apache.commons.io.IOUtils.DIR_SEPARATOR_WINDOWS : '?') != '\\') {
                    byte b3 = bytes[length2];
                    bytes[length2] = bytes[length3];
                    bytes[length3] = b3;
                    length2++;
                    length3--;
                } else {
                    byte b4 = bytes[length2];
                    bytes[length2] = bytes[length3];
                    bytes[length3] = b4;
                    length2 += 16;
                    length3 += 40;
                }
            }
            byte[] bArrCopyOf = java.util.Arrays.copyOf(bytes, bytes.length / 2);
            byte[] bArrCopyOfRange = java.util.Arrays.copyOfRange(bytes, bytes.length / 2, bytes.length);
            this.getLanguage = java.util.Arrays.copyOfRange(bytes, bytes.length / 2, bytes.length);
            java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
            messageDigest.update(bArrCopyOf);
            messageDigest.update(bArrCopyOfRange);
            if (new java.lang.String(java.lang.String.format("%1$032x", new java.math.BigInteger(1, messageDigest.digest())).getBytes()).equals(str2)) {
                return new java.lang.String(bArrCopyOf);
            }
            int i5 = getCrewEventsId + 109;
            getCrewEventsById = i5 % 128;
            int i6 = i5 % 2;
            return "";
        } catch (java.lang.IndexOutOfBoundsException e) {
            java.lang.String str3 = onAdLogged;
            java.lang.StringBuilder sb = new java.lang.StringBuilder("IndexOutOfBoundsException : ");
            sb.append(e.toString());
            aero.panasonic.inflight.services.utils.Log.e(str3, sb.toString());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c A[Catch: all -> 0x00b9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001a, B:12:0x0032, B:46:0x00a3, B:48:0x00a7, B:51:0x00b5, B:18:0x0040, B:20:0x0048, B:25:0x005f, B:27:0x0063, B:29:0x006f, B:39:0x0084, B:40:0x0085, B:45:0x0093, B:11:0x002c, B:31:0x0076), top: B:59:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005f A[Catch: all -> 0x00b9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001a, B:12:0x0032, B:46:0x00a3, B:48:0x00a7, B:51:0x00b5, B:18:0x0040, B:20:0x0048, B:25:0x005f, B:27:0x0063, B:29:0x006f, B:39:0x0084, B:40:0x0085, B:45:0x0093, B:11:0x002c, B:31:0x0076), top: B:59:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0093 A[Catch: all -> 0x00b9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001a, B:12:0x0032, B:46:0x00a3, B:48:0x00a7, B:51:0x00b5, B:18:0x0040, B:20:0x0048, B:25:0x005f, B:27:0x0063, B:29:0x006f, B:39:0x0084, B:40:0x0085, B:45:0x0093, B:11:0x002c, B:31:0x0076), top: B:59:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a7 A[Catch: all -> 0x00b9, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001a, B:12:0x0032, B:46:0x00a3, B:48:0x00a7, B:51:0x00b5, B:18:0x0040, B:20:0x0048, B:25:0x005f, B:27:0x0063, B:29:0x006f, B:39:0x0084, B:40:0x0085, B:45:0x0093, B:11:0x002c, B:31:0x0076), top: B:59:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5 A[Catch: all -> 0x00b9, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001a, B:12:0x0032, B:46:0x00a3, B:48:0x00a7, B:51:0x00b5, B:18:0x0040, B:20:0x0048, B:25:0x005f, B:27:0x0063, B:29:0x006f, B:39:0x0084, B:40:0x0085, B:45:0x0093, B:11:0x002c, B:31:0x0076), top: B:59:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken getAuthToken() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r2 = "getAuthToken() "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lb9
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r2 = r3.getDestinationName     // Catch: java.lang.Throwable -> Lb9
            r1.append(r2)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lb9
            aero.panasonic.inflight.services.utils.Log.v(r0, r1)     // Catch: java.lang.Throwable -> Lb9
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName     // Catch: java.lang.Throwable -> Lb9
            if (r0 == 0) goto L2c
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName     // Catch: java.lang.Throwable -> Lb9
            boolean r0 = r0.isExpired()     // Catch: java.lang.Throwable -> Lb9
            r1 = 53
            if (r0 == 0) goto L27
            r0 = 53
            goto L29
        L27:
            r0 = 66
        L29:
            if (r0 == r1) goto L2c
            goto L32
        L2c:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.setStartOffset()     // Catch: java.lang.Throwable -> Lb9
            r3.getDestinationName = r0     // Catch: java.lang.Throwable -> Lb9
        L32:
            boolean r0 = r3.setLanguage     // Catch: java.lang.Throwable -> Lb9
            r1 = 32
            if (r0 == 0) goto L3b
            r0 = 32
            goto L3d
        L3b:
            r0 = 49
        L3d:
            if (r0 == r1) goto L40
            goto La3
        L40:
            java.lang.String r0 = r3.mUserName     // Catch: java.lang.Throwable -> Lb9
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lb9
            if (r0 != 0) goto La3
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 + 15
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r1     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 % 2
            byte[] r0 = r3.setElapsedDuration     // Catch: java.lang.Throwable -> Lb9
            r1 = 95
            if (r0 == 0) goto L5b
            r0 = 59
            goto L5d
        L5b:
            r0 = 95
        L5d:
            if (r0 == r1) goto La3
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getEventType     // Catch: java.lang.Throwable -> Lb9
            if (r0 == 0) goto L93
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 + 35
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r1     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 % 2
            if (r0 == 0) goto L85
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getEventType     // Catch: java.lang.Throwable -> Lb9
            boolean r0 = r0.isExpired()     // Catch: java.lang.Throwable -> Lb9
            r1 = 0
            int r1 = r1.length     // Catch: java.lang.Throwable -> L83
            r1 = 38
            if (r0 == 0) goto L7e
            r0 = 21
            goto L80
        L7e:
            r0 = 38
        L80:
            if (r0 == r1) goto La3
            goto L93
        L83:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> Lb9
        L85:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getEventType     // Catch: java.lang.Throwable -> Lb9
            boolean r0 = r0.isExpired()     // Catch: java.lang.Throwable -> Lb9
            r1 = 1
            if (r0 == 0) goto L90
            r0 = 0
            goto L91
        L90:
            r0 = 1
        L91:
            if (r0 == r1) goto La3
        L93:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.setIsTerminating()     // Catch: java.lang.Throwable -> Lb9
            r3.getEventType = r0     // Catch: java.lang.Throwable -> Lb9
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 + 119
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r1     // Catch: java.lang.Throwable -> Lb9
            int r0 = r0 % 2
        La3:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getEventType     // Catch: java.lang.Throwable -> Lb9
            if (r0 == 0) goto Lb5
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getEventType     // Catch: java.lang.Throwable -> Lb9
            int r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId     // Catch: java.lang.Throwable -> Lb9
            int r1 = r1 + 39
            int r2 = r1 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r2     // Catch: java.lang.Throwable -> Lb9
            int r1 = r1 % 2
            monitor-exit(r3)
            return r0
        Lb5:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName     // Catch: java.lang.Throwable -> Lb9
            monitor-exit(r3)
            return r0
        Lb9:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.getAuthToken():aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken");
    }

    public void setSessionCredential(java.lang.String str, java.lang.String str2) {
        int i = getCrewEventsId + 105;
        getCrewEventsById = i % 128;
        if (!(i % 2 != 0)) {
            this.mUserName = str;
            this.setElapsedDuration = str2.getBytes();
            java.lang.Object[] objArr = null;
            int length = objArr.length;
            return;
        }
        this.mUserName = str;
        this.setElapsedDuration = str2.getBytes();
    }

    public synchronized void setCrewToken(aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken authToken) {
        int i = getCrewEventsId + 89;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "setting Crew Token ");
        this.getEventType = authToken;
        int i3 = getCrewEventsById + 71;
        getCrewEventsId = i3 % 128;
        if ((i3 % 2 != 0 ? (char) 16 : 'I') != 'I') {
            int i4 = 3 / 0;
        }
    }

    private synchronized aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken setStartOffset() {
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken authToken;
        int i = getCrewEventsById + 119;
        getCrewEventsId = i % 128;
        int i2 = i % 2;
        if ((this.logImpression ? 'B' : kotlin.text.Typography.less) != '<') {
            int i3 = getCrewEventsById + 21;
            getCrewEventsId = i3 % 128;
            if ((i3 % 2 != 0 ? 'C' : '7') != 'C') {
                this.getDestinationName = setUploadProgressPercent();
            } else {
                this.getDestinationName = setUploadProgressPercent();
                int i4 = 52 / 0;
            }
        }
        authToken = this.getDestinationName;
        int i5 = getCrewEventsById + 93;
        getCrewEventsId = i5 % 128;
        int i6 = i5 % 2;
        return authToken;
    }

    private java.lang.String FileUploadJson() {
        if (!(this.mGroundMode)) {
            return "secure.inflightpanasonic.aero";
        }
        int i = getCrewEventsById + 27;
        getCrewEventsId = i % 128;
        int i2 = i % 2;
        java.lang.String grndServerHostName = aero.panasonic.inflight.services.utils.HostNameManager.getGrndServerHostName();
        int i3 = getCrewEventsId + 83;
        getCrewEventsById = i3 % 128;
        if ((i3 % 2 == 0 ? (char) 21 : org.apache.commons.io.IOUtils.DIR_SEPARATOR_UNIX) == '/') {
            return grndServerHostName;
        }
        java.lang.Object obj = null;
        super.hashCode();
        return grndServerHostName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:

        if (getFileUploadJson().equals("") != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:

        if ((!r1) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:

        aero.panasonic.inflight.services.utils.Log.v(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "challenge()");
        r1 = new java.lang.StringBuilder();
        r1.append("https://");
        r1.append(FileUploadJson());
        r1.append("/inflight/services/auth/v1/challenge");
        r3 = new java.util.HashMap();
        r3.put("AI", getAirlineId());
        r1 = TestHelper(r1.toString(), r3);
        r3 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged;
        r5 = new java.lang.StringBuilder("challenge statusCode: ");
        r5.append(r1.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(r3, r5.toString());
        r3 = r1.AirportInfoConstant;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009e, code lost:

        if (r3 == (-1)) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a2, code lost:

        if (r3 == 200) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a6, code lost:

        if (r3 == 204) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:

        if (r3 == 400) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ae, code lost:

        if (r3 == 429) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b0, code lost:

        switch(r3) {
            case 403: goto L41;
            case 404: goto L39;
            case 405: goto L37;
            default: goto L36;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b3, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:

        return setStartOffset();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b9, code lost:

        r7.logImpression = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bb, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bc, code lost:

        r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById + 9;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r0 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c6, code lost:

        if ((r0 % 2) == 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:

        super.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cb, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ce, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d1, code lost:

        return r7.AdvertisementLogger;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d2, code lost:

        r1 = r1.executeAsync;
        r7.setEventType = r1.optLong("counter");
        r1 = r1.optString("challenge");
        r3 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged;
        r4 = new java.lang.StringBuilder("get method returned: counter: ");
        r4.append(r7.setEventType);
        r4.append(", challenge: ");
        r4.append(r1);
        aero.panasonic.inflight.services.utils.Log.i(r3, r4.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0109, code lost:

        return buildDataBundle(isUiRunningTest(r1, r7.getLanguage));
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x010a, code lost:

        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x010b, code lost:

        r3.printStackTrace();
        aero.panasonic.inflight.services.utils.Log.e(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "encode error: ".concat(java.lang.String.valueOf(r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x011b, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011c, code lost:

        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x011d, code lost:

        r3.printStackTrace();
        aero.panasonic.inflight.services.utils.Log.e(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "encode error: ".concat(java.lang.String.valueOf(r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x012d, code lost:

        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken setUploadProgressPercent() {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.setUploadProgressPercent():aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken");
    }

    private synchronized aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken setIsTerminating() {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "requestCrewAuthToken()");
        if (this.logImpression) {
            int i = getCrewEventsById + 33;
            getCrewEventsId = i % 128;
            if (i % 2 == 0) {
                this.getEventType = FileUploadManager();
            } else {
                this.getEventType = FileUploadManager();
                java.lang.Object[] objArr = null;
                int length = objArr.length;
            }
            int i2 = getCrewEventsId + 31;
            getCrewEventsById = i2 % 128;
            int i3 = i2 % 2;
        }
        return this.getEventType;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0046, code lost:

        if (r7.getDestinationName == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004f, code lost:

        if (r7.getDestinationName == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:

        aero.panasonic.inflight.services.utils.Log.v(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "crewChallenge()");
        r1 = new java.lang.StringBuilder();
        r1.append("https://");
        r1.append(FileUploadJson());
        r1.append("/inflight/services/cmi/auth/v1/challenge");
        r4 = new java.util.HashMap();
        r4.put("UN", r7.mUserName);
        r4.put(aero.panasonic.inflight.services.appauth.HttpAuthConnection.AT, r7.getDestinationName.getToken());
        r1 = TestHelper(r1.toString(), r4);
        r4 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged;
        r5 = new java.lang.StringBuilder("challenge statusCode: ");
        r5.append(r1.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(r4, r5.toString());
        r4 = r1.AirportInfoConstant;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a7, code lost:

        if (r4 == (-1)) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ab, code lost:

        if (r4 == 200) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00af, code lost:

        if (r4 == 204) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b3, code lost:

        if (r4 == 400) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b7, code lost:

        if (r4 == 429) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b9, code lost:

        switch(r4) {
            case 403: goto L45;
            case 404: goto L44;
            case 405: goto L42;
            default: goto L41;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bc, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c1, code lost:

        return setStartOffset();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c2, code lost:

        r7.logImpression = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c4, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c5, code lost:

        r7.setLanguage = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c9, code lost:

        return r7.AdvertisementLogger;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ca, code lost:

        r1 = r1.executeAsync;
        r7.setEventType = r1.optLong("counter");
        r1 = r1.optString("challenge");
        r3 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged;
        r4 = new java.lang.StringBuilder("get method returned: counter: ");
        r4.append(r7.setEventType);
        r4.append(", challenge: ");
        r4.append(r1);
        aero.panasonic.inflight.services.utils.Log.i(r3, r4.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0101, code lost:

        return isSeatBack(isUiRunningTest(r1, r7.setElapsedDuration));
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0102, code lost:

        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0103, code lost:

        r3.printStackTrace();
        aero.panasonic.inflight.services.utils.Log.e(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "encode error: ".concat(java.lang.String.valueOf(r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0113, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0114, code lost:

        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0115, code lost:

        r3.printStackTrace();
        aero.panasonic.inflight.services.utils.Log.e(aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged, "encode error: ".concat(java.lang.String.valueOf(r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0125, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0126, code lost:

        r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById + 117;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r0 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0130, code lost:

        if ((r0 % 2) == 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0132, code lost:

        r0 = r2.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0133, code lost:

        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0136, code lost:

        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken FileUploadManager() {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.FileUploadManager():aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public java.lang.String getAirlineId() {
        java.lang.String str;
        int i = getCrewEventsId + 17;
        getCrewEventsById = i % 128;
        java.lang.Object[] objArr = null;
        java.lang.Object[] objArr2 = 0;
        if (!(i % 2 != 0)) {
            str = this.AdvertisementItem;
            int length = (objArr2 == true ? 1 : 0).length;
        } else {
            str = this.AdvertisementItem;
        }
        int i2 = getCrewEventsById + 111;
        getCrewEventsId = i2 % 128;
        if (i2 % 2 == 0) {
            return str;
        }
        int length2 = objArr.length;
        return str;
    }

    public void setAirlineId(java.lang.String str) {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "setAirlineId() ".concat(java.lang.String.valueOf(str)));
        this.AdvertisementItem = str;
        if (!(str.contains("~"))) {
            this.getElapsedDuration = "";
            return;
        }
        int i = getCrewEventsById + 9;
        getCrewEventsId = i % 128;
        int i2 = i % 2;
        this.getElapsedDuration = str.substring(str.indexOf("~") + 1);
        int i3 = getCrewEventsId + 97;
        getCrewEventsById = i3 % 128;
        int i4 = i3 % 2;
    }

    public void setSecretKey(java.lang.String str) {
        int i = getCrewEventsId + 85;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        this.getLanguage = str.getBytes();
        int i3 = getCrewEventsById + 63;
        getCrewEventsId = i3 % 128;
        if (!(i3 % 2 != 0)) {
            return;
        }
        java.lang.Object obj = null;
        super.hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0021, code lost:

        if ((r4.getElapsedDuration == null) != true) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:

        r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById + 111;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002f, code lost:

        return r4.getElapsedDuration;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0030, code lost:

        r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId + 99;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r1 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:

        if ((r1 % 2) != 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:

        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:

        super.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:

        return "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:

        return "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:

        if ((r4.getElapsedDuration == null) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getFileUploadJson() {
        /*
            r4 = this;
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            int r0 = r0 + 125
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r1
            int r0 = r0 % 2
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1b
            java.lang.String r0 = r4.getElapsedDuration
            r3 = 67
            int r3 = r3 / r2
            if (r0 == 0) goto L16
            r1 = 0
        L16:
            if (r1 == 0) goto L23
            goto L30
        L19:
            r0 = move-exception
            throw r0
        L1b:
            java.lang.String r0 = r4.getElapsedDuration
            if (r0 == 0) goto L20
            goto L21
        L20:
            r2 = 1
        L21:
            if (r2 == r1) goto L30
        L23:
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            int r0 = r0 + 111
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r1
            int r0 = r0 % 2
            java.lang.String r0 = r4.getElapsedDuration
            return r0
        L30:
            java.lang.String r0 = ""
            int r1 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId
            int r1 = r1 + 99
            int r2 = r1 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r2
            int r1 = r1 % 2
            if (r1 != 0) goto L45
            r1 = 0
            super.hashCode()     // Catch: java.lang.Throwable -> L43
            return r0
        L43:
            r0 = move-exception
            throw r0
        L45:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.getFileUploadJson():java.lang.String");
    }

    public void setGroundMode(boolean z) {
        int i = getCrewEventsId + 103;
        getCrewEventsById = i % 128;
        int i2 = i % 2;
        if (this.mGroundMode == z) {
            return;
        }
        this.mGroundMode = z;
        this.getDestinationName = null;
        this.getEventType = null;
        this.logImpression = true;
        int i3 = getCrewEventsById + 79;
        getCrewEventsId = i3 % 128;
        int i4 = i3 % 2;
    }

    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken buildDataBundle(java.lang.String str) {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "postSolution");
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("https://");
        sb.append(FileUploadJson());
        sb.append("/inflight/services/auth/v1/solution");
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("airline_id", getAirlineId());
            long j = this.setEventType + 1;
            this.setEventType = j;
            jSONObject.put("counter", j);
            jSONObject.put("solution", str);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isSeatBack2 = isSeatBack(sb.toString(), jSONObject);
        java.lang.String str2 = onAdLogged;
        java.lang.StringBuilder sb2 = new java.lang.StringBuilder("postSolution statusCode: ");
        sb2.append(isSeatBack2.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(str2, sb2.toString());
        int i = isSeatBack2.AirportInfoConstant;
        java.lang.Object obj = null;
        if (i != -1) {
            if (i == 200) {
                org.json.JSONObject jSONObject2 = isSeatBack2.executeAsync;
                long jOptLong = jSONObject2.optLong("counter");
                java.lang.String strOptString = jSONObject2.optString("_t");
                java.lang.String strOptString2 = jSONObject2.optString("_e");
                java.lang.String str3 = onAdLogged;
                java.lang.StringBuilder sb3 = new java.lang.StringBuilder("post method returned: counterString: ");
                sb3.append(jOptLong);
                sb3.append(", token: ");
                sb3.append(strOptString);
                sb3.append(", expiry: ");
                sb3.append(strOptString2);
                aero.panasonic.inflight.services.utils.Log.i(str3, sb3.toString());
                if (jOptLong == this.setEventType + 1) {
                    return new aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken(strOptString, java.lang.Long.parseLong(strOptString2));
                }
                return null;
            }
            if (i == 203) {
                this.logImpression = false;
            } else if (i != 429) {
                if (i != 400 && i != 401) {
                    switch (i) {
                        case androidx.constraintlayout.core.motion.utils.TypedValues.Cycle.TYPE_ALPHA /* 403 */:
                        case 404:
                            break;
                        case 405:
                            return setStartOffset();
                        default:
                            int i2 = getCrewEventsById + 37;
                            getCrewEventsId = i2 % 128;
                            int i3 = i2 % 2;
                            return null;
                    }
                }
                this.logImpression = false;
            }
        }
        int i4 = getCrewEventsById + 125;
        getCrewEventsId = i4 % 128;
        if ((i4 % 2 != 0 ? (char) 15 : '\f') == '\f') {
            return null;
        }
        super.hashCode();
        return null;
    }

    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken isSeatBack(java.lang.String str) {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "postCewSolution");
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("https://");
        sb.append(FileUploadJson());
        sb.append("/inflight/services/cmi/auth/v1/solution");
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("username", this.mUserName);
            long j = this.setEventType + 1;
            this.setEventType = j;
            jSONObject.put("counter", j);
            jSONObject.put("solution", str);
            int i = getCrewEventsById + 81;
            getCrewEventsId = i % 128;
            if (i % 2 != 0) {
            }
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isSeatBack2 = isSeatBack(sb.toString(), jSONObject);
        java.lang.String str2 = onAdLogged;
        java.lang.StringBuilder sb2 = new java.lang.StringBuilder("postSolution statusCode: ");
        sb2.append(isSeatBack2.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(str2, sb2.toString());
        int i2 = isSeatBack2.AirportInfoConstant;
        if (i2 != -1) {
            if (i2 == 200) {
                org.json.JSONObject jSONObject2 = isSeatBack2.executeAsync;
                long jOptLong = jSONObject2.optLong("counter");
                java.lang.String strOptString = jSONObject2.optString("_t");
                java.lang.String strOptString2 = jSONObject2.optString("_e");
                java.lang.String str3 = onAdLogged;
                java.lang.StringBuilder sb3 = new java.lang.StringBuilder("post method returned: counterString: ");
                sb3.append(jOptLong);
                sb3.append(", token: ");
                sb3.append(strOptString);
                sb3.append(", expiry: ");
                sb3.append(strOptString2);
                aero.panasonic.inflight.services.utils.Log.i(str3, sb3.toString());
                if (jOptLong == this.setEventType + 1) {
                    return new aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken(strOptString, java.lang.Long.parseLong(strOptString2));
                }
                int i3 = getCrewEventsId + 29;
                getCrewEventsById = i3 % 128;
                if (i3 % 2 == 0) {
                    int i4 = 46 / 0;
                    return null;
                }
            } else if (i2 != 203 && i2 != 429 && i2 != 400 && i2 != 401) {
                switch (i2) {
                    case androidx.constraintlayout.core.motion.utils.TypedValues.Cycle.TYPE_ALPHA /* 403 */:
                    case 404:
                        break;
                    case 405:
                        return setIsTerminating();
                    default:
                        int i5 = getCrewEventsId + 29;
                        getCrewEventsById = i5 % 128;
                        int i6 = i5 % 2;
                        return null;
                }
            }
        }
        return null;
    }

    private boolean setIsSeatBack(aero.panasonic.inflight.services.appauth.HttpAuthConnection.AuthToken authToken) {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "validAuthToken()");
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("https://");
        sb.append(FileUploadJson());
        sb.append("/inflight/services/auth/v1/validate_token");
        java.util.HashMap map = new java.util.HashMap();
        map.put("AI", getAirlineId());
        map.put(AT, authToken.getToken());
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isseatbackTestHelper = TestHelper(sb.toString(), map);
        java.lang.String str = onAdLogged;
        java.lang.StringBuilder sb2 = new java.lang.StringBuilder("validAuthToken statusCode: ");
        sb2.append(isseatbackTestHelper.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(str, sb2.toString());
        int i = isseatbackTestHelper.AirportInfoConstant;
        if (i == 200 || i == 204) {
            int i2 = getCrewEventsById + 101;
            getCrewEventsId = i2 % 128;
            int i3 = i2 % 2;
            return true;
        }
        if (i != 429) {
            if (i == 400) {
                this.logImpression = false;
                int i4 = getCrewEventsId + 109;
                getCrewEventsById = i4 % 128;
                int i5 = i4 % 2;
            } else if (i != 401) {
                if (i != 404) {
                    if (i != 405) {
                        return false;
                    }
                    int i6 = getCrewEventsById + 115;
                    getCrewEventsId = i6 % 128;
                    int i7 = i6 % 2;
                    return false;
                }
                this.logImpression = false;
                int i42 = getCrewEventsId + 109;
                getCrewEventsById = i42 % 128;
                int i52 = i42 % 2;
            }
        }
        return false;
    }

    private boolean ackReceived() {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "validCrewAuthToken()");
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("https://");
        sb.append(FileUploadJson());
        sb.append("/inflight/services/cmi/auth/v1/validate_token");
        java.util.HashMap map = new java.util.HashMap();
        map.put("UN", this.mUserName);
        map.put(AT, this.getEventType.getToken());
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isseatbackTestHelper = TestHelper(sb.toString(), map);
        java.lang.String str = onAdLogged;
        java.lang.StringBuilder sb2 = new java.lang.StringBuilder("validAuthToken statusCode: ");
        sb2.append(isseatbackTestHelper.AirportInfoConstant);
        aero.panasonic.inflight.services.utils.Log.v(str, sb2.toString());
        int i = isseatbackTestHelper.AirportInfoConstant;
        if (i == 200 || i == 204) {
            return true;
        }
        if (i != 429) {
            if (i == 400) {
                this.setLanguage = false;
                int i2 = getCrewEventsById + 97;
                getCrewEventsId = i2 % 128;
                int i3 = i2 % 2;
            } else if (i != 401) {
                if (i != 404) {
                    if (i != 405) {
                        return false;
                    }
                    int i4 = getCrewEventsById + 65;
                    getCrewEventsId = i4 % 128;
                    int i5 = i4 % 2;
                    return false;
                }
                this.setLanguage = false;
                int i22 = getCrewEventsById + 97;
                getCrewEventsId = i22 % 128;
                int i32 = i22 % 2;
            }
        }
        return false;
    }

    private java.lang.String isUiRunningTest(java.lang.String str, byte[] bArr) throws java.security.NoSuchAlgorithmException, java.security.InvalidKeyException {
        javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(bArr, this.mGroundMode ? "HmacSHA1" : "HmacSHA256");
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance(this.mGroundMode ? "HmacSHA1" : "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] bArrDoFinal = mac.doFinal(str.getBytes());
        if (!this.mGroundMode) {
            bArrDoFinal = bytesToHex(bArrDoFinal).getBytes();
        }
        return android.util.Base64.encodeToString(bArrDoFinal, this.mGroundMode ? 2 : 10).trim();
    }

    public static java.lang.String bytesToHex(byte[] bArr) {
        char[] cArr;
        char[] cArr2;
        int i = getCrewEventsId + 7;
        getCrewEventsById = i % 128;
        int i2 = 0;
        if (!(i % 2 != 0)) {
            cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            cArr2 = new char[bArr.length << 4];
            i2 = 1;
        } else {
            cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            cArr2 = new char[bArr.length * 2];
        }
        while (true) {
            if ((i2 < bArr.length ? 'I' : (char) 16) == 'I') {
                int i3 = bArr[i2] & kotlin.UByte.MAX_VALUE;
                int i4 = i2 * 2;
                cArr2[i4] = cArr[i3 >>> 4];
                cArr2[i4 + 1] = cArr[i3 & 15];
                i2++;
            } else {
                java.lang.String str = new java.lang.String(cArr2);
                int i5 = getCrewEventsId + 61;
                getCrewEventsById = i5 % 128;
                int i6 = i5 % 2;
                return str;
            }
        }
    }

    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack TestHelper(java.lang.String str, java.util.Map<java.lang.String, java.lang.String> map) {
        javax.net.ssl.TrustManagerFactory trustManagerFactory;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isseatback = new aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack((byte) 0);
        try {
            java.net.URL url = new java.net.URL(str);
            javax.net.ssl.HttpsURLConnection httpsURLConnection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            if (!(aero.panasonic.inflight.services.utils.ServiceUtil.isGroundMode(url.getHost()))) {
                trustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getTrustManagerFactory();
                int i = getCrewEventsById + 43;
                getCrewEventsId = i % 128;
                int i2 = i % 2;
            } else {
                int i3 = getCrewEventsId + 51;
                getCrewEventsById = i3 % 128;
                if (i3 % 2 != 0) {
                    trustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getGroundTrustManagerFactory();
                } else {
                    trustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getGroundTrustManagerFactory();
                    int i4 = 84 / 0;
                }
            }
            try {
                this.AdvertisementLogger$Listener.init(null, trustManagerFactory.getTrustManagers(), null);
            } catch (java.security.KeyManagementException e) {
                e.printStackTrace();
            }
            httpsURLConnection.setSSLSocketFactory(this.AdvertisementLogger$Listener.getSocketFactory());
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(6000);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setUseCaches(false);
            java.util.Iterator<java.lang.String> it = map.keySet().iterator();
            while (true) {
                if ((it.hasNext() ? (char) 0 : kotlin.text.Typography.greater) != 0) {
                    break;
                }
                java.lang.String next = it.next();
                httpsURLConnection.setRequestProperty(next, map.get(next));
            }
            isseatback.AirportInfoConstant = httpsURLConnection.getResponseCode();
            if (isseatback.AirportInfoConstant != 200) {
                java.lang.String str2 = onAdLogged;
                java.lang.StringBuilder sb = new java.lang.StringBuilder("failed ");
                sb.append(isseatback.AirportInfoConstant);
                sb.append(com.fasterxml.jackson.core.util.MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(httpsURLConnection.getResponseMessage());
                aero.panasonic.inflight.services.utils.Log.e(str2, sb.toString());
            } else {
                java.lang.String strIsSeatBack = isSeatBack(httpsURLConnection.getInputStream());
                aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "response = ".concat(java.lang.String.valueOf(strIsSeatBack)));
                isseatback.executeAsync = new org.json.JSONObject(strIsSeatBack);
                httpsURLConnection.disconnect();
            }
            return isseatback;
        } catch (java.io.IOException e2) {
            aero.panasonic.inflight.services.utils.Log.exception(e2);
            return isseatback;
        } catch (org.json.JSONException e3) {
            aero.panasonic.inflight.services.utils.Log.exception(e3);
            return isseatback;
        }
    }

    private aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isSeatBack(java.lang.String str, org.json.JSONObject jSONObject) {
        javax.net.ssl.TrustManagerFactory groundTrustManagerFactory;
        aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack isseatback = new aero.panasonic.inflight.services.appauth.HttpAuthConnection.isSeatBack((byte) 0);
        try {
            java.net.URL url = new java.net.URL(str);
            javax.net.ssl.HttpsURLConnection httpsURLConnection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            if ((aero.panasonic.inflight.services.utils.ServiceUtil.isGroundMode(url.getHost()) ? 'X' : 'B') == 'X') {
                groundTrustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getGroundTrustManagerFactory();
            } else {
                groundTrustManagerFactory = aero.panasonic.inflight.services.security.CertificateKeyManager.getTrustManagerFactory();
            }
            try {
                this.AdvertisementLogger$Listener.init(null, groundTrustManagerFactory.getTrustManagers(), null);
                int i = getCrewEventsId + 23;
                getCrewEventsById = i % 128;
                if (i % 2 == 0) {
                }
            } catch (java.security.KeyManagementException e) {
                e.printStackTrace();
            }
            httpsURLConnection.setSSLSocketFactory(this.AdvertisementLogger$Listener.getSocketFactory());
            httpsURLConnection.setConnectTimeout(6000);
            httpsURLConnection.setDoOutput(true);
            setIsSeatBack(httpsURLConnection.getOutputStream(), jSONObject);
            isseatback.AirportInfoConstant = httpsURLConnection.getResponseCode();
            if (isseatback.AirportInfoConstant == 200) {
                java.lang.String strIsSeatBack = isSeatBack(httpsURLConnection.getInputStream());
                aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "response = ".concat(java.lang.String.valueOf(strIsSeatBack)));
                isseatback.executeAsync = new org.json.JSONObject(strIsSeatBack);
                httpsURLConnection.disconnect();
            } else {
                java.lang.String str2 = onAdLogged;
                java.lang.StringBuilder sb = new java.lang.StringBuilder("failed ");
                sb.append(isseatback.AirportInfoConstant);
                sb.append(com.fasterxml.jackson.core.util.MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(httpsURLConnection.getResponseMessage());
                aero.panasonic.inflight.services.utils.Log.e(str2, sb.toString());
                int i2 = getCrewEventsById + 1;
                getCrewEventsId = i2 % 128;
                int i3 = i2 % 2;
            }
            return isseatback;
        } catch (java.io.IOException e2) {
            aero.panasonic.inflight.services.utils.Log.exception(e2);
            return isseatback;
        } catch (org.json.JSONException e3) {
            aero.panasonic.inflight.services.utils.Log.exception(e3);
            return isseatback;
        }
    }

    private static void setIsSeatBack(java.io.OutputStream outputStream, org.json.JSONObject jSONObject) throws java.io.IOException {
        java.io.DataOutputStream dataOutputStream = new java.io.DataOutputStream(outputStream);
        dataOutputStream.writeBytes(jSONObject.toString());
        dataOutputStream.flush();
        dataOutputStream.close();
        int i = getCrewEventsById + 71;
        getCrewEventsId = i % 128;
        if (!(i % 2 != 0)) {
            return;
        }
        java.lang.Object[] objArr = null;
        int length = objArr.length;
    }

    private static java.lang.String isSeatBack(java.io.InputStream inputStream) throws java.io.IOException {
        aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "convertInputStreamToString()");
        java.lang.String string = "";
        if (inputStream != null) {
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));
            while (true) {
                java.lang.String line = bufferedReader.readLine();
                if (line != null) {
                    java.lang.StringBuilder sb = new java.lang.StringBuilder();
                    sb.append(string);
                    sb.append(line);
                    string = sb.toString();
                    int i = getCrewEventsId + 101;
                    getCrewEventsById = i % 128;
                    if (i % 2 == 0) {
                    }
                } else {
                    inputStream.close();
                    return string;
                }
            }
        } else {
            int i2 = getCrewEventsById + 91;
            getCrewEventsId = i2 % 128;
            if (i2 % 2 != 0) {
                java.lang.Object obj = null;
                super.hashCode();
            }
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void onConnectionReset() {
        /*
            r3 = this;
            java.lang.String r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged
            java.lang.String r1 = "onConnectionReset()"
            aero.panasonic.inflight.services.utils.Log.v(r0, r1)
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName
            if (r0 == 0) goto L40
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            r1 = 35
            int r0 = r0 + r1
            int r2 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r2
            int r0 = r0 % 2
            r2 = 74
            if (r0 == 0) goto L1d
            r0 = 74
            goto L1f
        L1d:
            r0 = 31
        L1f:
            if (r0 == r2) goto L2a
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName
            boolean r0 = r3.setIsSeatBack(r0)
            if (r0 != 0) goto L46
            goto L40
        L2a:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.getDestinationName
            boolean r0 = r3.setIsSeatBack(r0)
            r2 = 0
            super.hashCode()     // Catch: java.lang.Throwable -> L3e
            r2 = 69
            if (r0 != 0) goto L39
            goto L3b
        L39:
            r1 = 69
        L3b:
            if (r1 == r2) goto L46
            goto L40
        L3e:
            r0 = move-exception
            throw r0
        L40:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.setStartOffset()
            r3.getDestinationName = r0
        L46:
            r0 = 1
            r3.setLanguage = r0
            byte[] r0 = r3.setElapsedDuration
            if (r0 == 0) goto L6c
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            int r0 = r0 + 65
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L66
            boolean r0 = r3.ackReceived()
            r1 = 90
            int r1 = r1 / 0
            if (r0 != 0) goto L72
            goto L6c
        L64:
            r0 = move-exception
            throw r0
        L66:
            boolean r0 = r3.ackReceived()
            if (r0 != 0) goto L72
        L6c:
            aero.panasonic.inflight.services.appauth.HttpAuthConnection$AuthToken r0 = r3.setIsTerminating()
            r3.getEventType = r0
        L72:
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            int r0 = r0 + 11
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r1
            int r0 = r0 % 2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.onConnectionReset():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            int r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId
            int r0 = r0 + 13
            int r1 = r0 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            java.lang.String r3 = "onServiceConnected() "
            if (r0 == r2) goto L2d
            java.lang.String r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r5 = r3.concat(r5)
            aero.panasonic.inflight.services.utils.Log.v(r0, r5)
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi r5 = aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi.Stub.asInterface(r6)
            r4.getZonePath = r5
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi r5 = r4.getZonePath
            if (r5 == 0) goto L76
            goto L4c
        L2d:
            java.lang.String r0 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r5 = r3.concat(r5)
            aero.panasonic.inflight.services.utils.Log.v(r0, r5)
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi r5 = aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi.Stub.asInterface(r6)
            r4.getZonePath = r5
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi r5 = r4.getZonePath
            r6 = 0
            super.hashCode()     // Catch: java.lang.Throwable -> L81
            if (r5 == 0) goto L49
            r1 = 1
        L49:
            if (r1 == r2) goto L4c
            goto L76
        L4c:
            int r5 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId
            int r5 = r5 + 85
            int r6 = r5 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById = r6
            int r5 = r5 % 2
            java.lang.String r5 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged
            java.lang.String r6 = "bind to IfeService succeed"
            aero.panasonic.inflight.services.utils.Log.v(r5, r6)
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            r4.AdLoggerController$RequestHandler$MessageId = r5
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceApi r5 = r4.getZonePath     // Catch: android.os.RemoteException -> L6b
            int r6 = r4.getDestinationType     // Catch: android.os.RemoteException -> L6b
            aero.panasonic.inflight.services.ifeservice.aidl.IIfeServiceEventCallback$Stub r0 = r4.RequestBase     // Catch: android.os.RemoteException -> L6b
            r5.registerIfeSystemEvent(r6, r0)     // Catch: android.os.RemoteException -> L6b
            return
        L6b:
            r5 = move-exception
            r5.printStackTrace()
            java.lang.String r5 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.onAdLogged
            java.lang.String r6 = "IfeServiceConnection.onServiceConnected() subscribeFlightMapImageEvent error!"
            aero.panasonic.inflight.services.utils.Log.e(r5, r6)
        L76:
            int r5 = aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsById
            int r5 = r5 + 41
            int r6 = r5 % 128
            aero.panasonic.inflight.services.appauth.HttpAuthConnection.getCrewEventsId = r6
            int r5 = r5 % 2
            return
        L81:
            r5 = move-exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: aero.panasonic.inflight.services.appauth.HttpAuthConnection.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(android.content.ComponentName componentName) {
        int i = getCrewEventsId + 17;
        getCrewEventsById = i % 128;
        if (!(i % 2 == 0)) {
            aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "onServiceDisconnected() ".concat(java.lang.String.valueOf(componentName)));
            this.AdLoggerController$RequestHandler$MessageId = null;
        } else {
            aero.panasonic.inflight.services.utils.Log.v(onAdLogged, "onServiceDisconnected() ".concat(java.lang.String.valueOf(componentName)));
            this.AdLoggerController$RequestHandler$MessageId = null;
            int i2 = 41 / 0;
        }
    }

    public static class AuthToken {
        private final long onError;
        private final java.lang.String onSuccess;

        public AuthToken(java.lang.String str, long j) {
            this.onSuccess = str;
            this.onError = java.lang.System.currentTimeMillis() + (j * 1000);
        }

        public boolean isExpired() {
            return this.onError < java.lang.System.currentTimeMillis();
        }

        public java.lang.String getToken() {
            return this.onSuccess;
        }

        public java.lang.String toString() {
            java.lang.StringBuilder sb = new java.lang.StringBuilder("AuthToken {token = ");
            sb.append(this.onSuccess);
            sb.append(", expiration timestamp = ");
            sb.append(this.onError);
            sb.append("}");
            return sb.toString();
        }
    }

    static class isSeatBack {
        int AirportInfoConstant;
        org.json.JSONObject executeAsync;

        private isSeatBack() {
        }

        /* synthetic */ isSeatBack(byte b2) {
            this();
        }
    }

    private static java.lang.String buildDataBundle(java.lang.String str, int i) {
        java.lang.String str2;
        java.lang.Object charArray = str;
        if (str != null) {
            charArray = str.toCharArray();
        }
        char[] cArr = (char[]) charArray;
        synchronized (b.d.c.getFileName.addImage) {
            b.d.c.getFileName.getPrevious = i;
            char[] cArr2 = new char[cArr.length];
            b.d.c.getFileName.getNext = 0;
            while (b.d.c.getFileName.getNext < cArr.length) {
                cArr2[b.d.c.getFileName.getNext] = (char) (((long) (cArr[b.d.c.getFileName.getNext] ^ (b.d.c.getFileName.getNext * b.d.c.getFileName.getPrevious))) ^ getContentType);
                b.d.c.getFileName.getNext++;
            }
            str2 = new java.lang.String(cArr2);
        }
        return str2;
    }
}
