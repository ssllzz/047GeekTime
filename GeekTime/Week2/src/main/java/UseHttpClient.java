import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class UseHttpClient {

    public static void main(String[] args) {
        //设置url
        String url="http://127.0.0.1:8808/";
        String requestBody="";
        long startTime = System.currentTimeMillis();
        String response=sendMessage(url);
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;

        System.out.println("请求耗时 "+ useTime);
    }

    private static String sendMessage(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String responseBody = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
            System.out.println("responseBody = " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

}
