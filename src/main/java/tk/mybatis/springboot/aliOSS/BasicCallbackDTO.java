package tk.mybatis.springboot.aliOSS;

import java.util.Map;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 10:47
 * 描    述 :
 */
public class BasicCallbackDTO {
    /** 回调地址 */
    private String url;
    /** 回调服务器名称 */
    private String name;
    /** 回调参数 */
    private Map<String,String> params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
