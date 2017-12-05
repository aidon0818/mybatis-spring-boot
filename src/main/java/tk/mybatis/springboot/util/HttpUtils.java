package tk.mybatis.springboot.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/12/5 10:34
 * 描    述 :
 */
public class HttpUtils {
    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static <T> CloseableHttpResponse postData(CloseableHttpClient client, String url, String type, T data) {
        assert ((url != null) && (type != null) && (data != null));
        final String requestBodyType = "application/json";
        final String defaultVarName = "var";

        //建立参数
        StringEntity entity = null;
        if (requestBodyType.equals(type)) {
            entity = new StringEntity(JsonUtils.obj2Json(data), StandardCharsets.UTF_8);
        } else {
            List<NameValuePair> params = new ArrayList<>();
            if (data instanceof Map) {
                for (Map.Entry<?, ?> item : ((Map<?, ?>) data).entrySet()) {
                    params.add(new BasicNameValuePair(item.getKey().toString(), JsonUtils.obj2Json(item.getValue())));
                }
            } else {
                params.add(new BasicNameValuePair(defaultVarName, JsonUtils.obj2Json(data)));
            }
            entity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
        }
        entity.setContentEncoding("UTF-8");
        entity.setContentType(type);

        // 发起Post请求
        HttpPost post = new HttpPost(url);
        post.setHeader(HTTP.CONTENT_TYPE, type);
        post.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            //获取post返回值
            HttpEntity httpEntity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (httpEntity != null) {
                System.out.println("Response content length: " + httpEntity.getContentLength());
                System.out.println(EntityUtils.toString(httpEntity));
                EntityUtils.consume(httpEntity);
            }
        } catch (IOException e) {
        }
        return response;
    }

}
