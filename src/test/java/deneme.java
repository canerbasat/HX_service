import base.BaseMethods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class deneme {
    public static void main(String[] args) throws Exception {
       // URL url = new URL("https://app-gib-prod-app-gip-test.azurewebsites.net/api/Despatch/getIncomingDespatchUblDownload");
       URL url = new URL("https://app-gib-prod-app-gip-test.azurewebsites.net/api/Invoice/getIncomingUblDownloadInvoices");


        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        String getResponse;
        http.setRequestMethod("GET"); // PUT is another valid option
        http.setDoOutput(true);
        System.out.println(http.getContent().toString());

        System.out.println(http.getResponseMessage());
        System.out.println(http.getResponseCode());
        System.out.println(http.getRequestMethod());
       // System.out.println(http.getErrorStream().read());

        BufferedReader br = null;
        if (http.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                System.out.println(strCurrentLine);
            }
        } else {
            br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                System.out.println(strCurrentLine);
            }
        }


    }
}
