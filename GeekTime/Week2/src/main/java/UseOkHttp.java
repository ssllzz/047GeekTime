import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UseOkHttp {
    public static void main(String[] args) {

        //设置url
        String url = "http://127.0.0.1:8808/";
        String requestBody = "";
        long startTime = System.currentTimeMillis();
        String response = sendMessage(url);
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;

        System.out.println("请求耗时 " + useTime);
    }

    private static String sendMessage(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request  = new Request.Builder().url(url).build();
        String responseBody = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
            System.out.println("response = " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}

