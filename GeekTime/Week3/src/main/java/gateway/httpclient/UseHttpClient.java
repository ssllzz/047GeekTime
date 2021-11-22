package gateway.httpclient;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class UseHttpClient {

    public static void main(String[] args) {
        //设置url
        String url="http://127.0.0.1:8888/open";
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
            int code = response.getCode();
            System.out.println("code = " + code);
            if (code > 200) {
                HttpEntity entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
                System.out.println("responseBody = " + responseBody);

            }else {
                HttpEntity entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);

                Header kk = response.getHeader("kk");
                String value = kk.getValue();
                System.out.println("responseBody = " + responseBody);
                System.out.println("header value mao = " + value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

}
