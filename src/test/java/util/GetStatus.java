package util;

import base.BaseMethods;
import io.restassured.RestAssured;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;

public class GetStatus {
    private static Logger log = Logger.getLogger(GetStatus.class);
    private static String logMessage = "";
    private static String jsonString= "";

    public static void getStatus() throws IOException {
        BaseMethods baseMethods = new BaseMethods();
        log.info(String.format(new Object() {
        }.getClass().getEnclosingMethod().getName() + "service started."));

        HttpUriRequest request = new HttpGet(RestAssured.baseURI + "swagger.json");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        if (httpResponse.getStatusLine().getStatusCode() >= 400) {
            log.info(String.format(new Object() {
            }.getClass().getEnclosingMethod().getName() + " service response returned: " + responseBody));
            throw new AssertionError("service response returned: " + httpResponse.getStatusLine().getStatusCode() + " - " + httpResponse.getStatusLine().getReasonPhrase() + "/Error Message : " + responseBody);
        } else {
            log.info(String.format(new Object() {
            }.getClass().getEnclosingMethod().getName() + " service response returned: " + httpResponse.getStatusLine().getStatusCode() + " - " + httpResponse.getStatusLine().getReasonPhrase() + "\n" + baseMethods.jsonBeauty(responseBody)));
        }
    }

}

