package tk.mybatis.springboot.aliOSS;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 10:46
 * 描    述 :
 */
public class BasicFileDTO {
    /** 文件空间，即阿里云的buchet或FastFDS的group */
    private String scope;
    /** 文件标志，即阿里云的key或FastFDS的key */
    private String key;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
