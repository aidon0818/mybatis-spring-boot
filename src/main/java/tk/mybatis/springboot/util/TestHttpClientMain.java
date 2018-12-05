package tk.mybatis.springboot.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


/**
 * @Auther: ld
 * @Date: 2018/12/5 16:16
 * @Param ${tags}
 * @Description:
 */

public class TestHttpClientMain {
    static public int num = 0;

    @Test
    public static void main(String[] args) {
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://localhost:8085/gxb/order/queryBalance");
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(100).setConnectionRequestTimeout(100)
//                .setSocketTimeout(100).build();
//        StringEntity entity1 = new StringEntity("", "UTF-8");
//        entity1.setContentType("application/json");
//        httpPost.setEntity(entity1);
//        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
//        try {
//            HttpResponse response = httpClient.execute(httpPost);
//            byte[] contentInBytes = new byte[1024];
//            int statusCode = response.getStatusLine().getStatusCode();
//            response.getEntity().getContent().read(contentInBytes);
//            String res = new String(contentInBytes,"UTF-8");
//            System.out.println("statusCode:" + statusCode + ";content:" + res);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8085/gxb/order/queryBalance");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println("得到的结果:" + response.getStatusLine());//得到请求结果  
            HttpEntity entity = response.getEntity();//得到请求回来的数据
        } catch (IOException e) {
            if ("Read timed out".equals(e.getLocalizedMessage())) {
                System.out.println("链接超时");
                TestHttpClientMain.sendHttp();

            }

        }
    }

    private static String sendHttp() {
        CloseableHttpResponse response = null;
        while (num < 5) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8085/gxb/order/queryBalance");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            try {
                response = httpclient.execute(httpPost);
                System.out.println("得到的结果:" + response.getStatusLine());//得到请求结果  
                HttpEntity entity = response.getEntity();//得到请求回来的数据
                break;
            } catch (IOException e) {
                if ("Read timed out".equals(e.getLocalizedMessage())) {
                    System.out.println("链接超时2222");
                    num++;
                }
            }
        }
        return response.getStatusLine().toString();
    }
}
