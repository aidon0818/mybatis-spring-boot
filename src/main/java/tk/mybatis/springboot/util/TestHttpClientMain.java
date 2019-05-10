package tk.mybatis.springboot.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * @Auther: ld
 * @Date: 2018/12/5 16:16
 * @Param ${tags}
 * @Description:
 */

public class TestHttpClientMain {
    static public int num = 0;
    static Logger log = LoggerFactory.getLogger(TestHttpClientMain.class);

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8085/gxb/order/queryBalance");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            byte[] contentInBytes = new byte[1024];
            int statusCode = response.getStatusLine().getStatusCode();
            response.getEntity().getContent().read(contentInBytes);
            String res = new String(contentInBytes, "UTF-8");
            System.out.println("statusCode:" + statusCode + ";content:" + res);
        } catch (Exception e) {
            log.error(e.getMessage());
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
