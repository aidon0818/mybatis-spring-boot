package tk.mybatis.springboot.aliOSS;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 11:03
 * 描    述 :
 */
@Component
@ConfigurationProperties(prefix = "aliyunoss")
public class OSSClientUtils {
    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private long idleConnectionTime;

    private ClientConfiguration conf = new ClientConfiguration();

    private static OSSClient client = null;

    public static synchronized OSSClient getInstance() {
        if (client == null) {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        return client;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public OSSClient getClient() {
        return client;
    }

    public void setClient(OSSClient client) {
        this.client = client;
    }

    public long getIdleConnectionTime() {
        return idleConnectionTime;
    }

    public void setIdleConnectionTime(long idleConnectionTime) {
        this.idleConnectionTime = idleConnectionTime;
    }
}
