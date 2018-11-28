package pe.edu.upc.warehouse.util;

public class BackendSetting {

    public static final String APPLICATION_ID = "AAA1A13F-2093-0782-FF86-F0A444453000";
    public static final String REST_API_KEY = "7EE29BDC-AC19-D81E-FF8D-5ED00CBC8000";
    public static final String ANDROID_API_KEY = "5EF4A281-B9A6-15A9-FF76-E0D7A7E1B500";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static String getServerUrl(){
        return String.format(SERVER_URL.concat("/{}/{}/"),APPLICATION_ID, REST_API_KEY);
    }

}
