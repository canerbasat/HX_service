package base;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.lang.Math;
import java.util.Random;


public  class BaseMethods {

    private Logger log = Logger.getLogger(BaseApi.class);
    private String logMessage = "";


    public static String jsonBeauty(String uglyString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    public static String valueFromGsonWithKey(String responseBody,String key) {
        Gson g = new Gson();
        JsonElement element = g.fromJson (responseBody, JsonElement.class); //Converts the json string to JsonElement without POJO
        JsonObject jsonObj = element.getAsJsonObject(); //Converting JsonElement to JsonObject
        return jsonObj.get(key).getAsString();
    }






    public static String  randomNumberBetweenTwoNumbers(int start,int end) {
        int min = start;
        int max = end;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        System.out.println(random_int);
        String randomGeneratedInt = String.valueOf(random_int);
        return randomGeneratedInt;
    }

    public static String randomStringWithlength(int stringLength){

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz".toCharArray();
        String stringRandom = "";
        for (int i = 0; i < stringLength; i++) {
            stringRandom = stringRandom + String.valueOf(chars[random.nextInt(chars.length)]);
        }
        return stringRandom;
    }

    public static void getResponse(String endPoint) throws IOException {
        URL url = new URL("https://app-gib-prod-app-gip-test.azurewebsites.net/api/Despatch/getIncomingDespatchUblDownload");
        //URL url = new URL("https://app-gib-prod-app-gip-test.azurewebsites.net/api/Invoice/getIncomingUblDownloadInvoices");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("GET"); // PUT is another valid option
        http.setDoOutput(true);
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
