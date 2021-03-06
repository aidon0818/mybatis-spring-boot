package tk.mybatis.springboot.aliOSS;

import java.util.Map;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 10:47
 * 描    述 :
 */
public class BasicDownloadRequestDTO {
    /** 下载申请的唯一编号 */
    private Integer requestId;
    /** 下载任务ID（没有作用，将被取消） */
    private String downloadId; //没有作用，将被取消
    /** 空间(bucket或group) */
    private String scope;
    /** 文件id(key或path) */
    private String key;
    /** 申请下载的分片序号，与分片大小相乘计算出文件起始位置 */
    private Integer chunkId;
    /** 申请下载的分片大小，如果为0则chunkId失效，下载文件所有内容 */
    private Integer chunkSize;
    /** 分片的约定大小，如果为0则下载后续所有内容（与chunkSize重复，将被取消） */
    private Integer chunkPerSize; //与chunkSize重复，将被取消
    /** 其他下载参数 */
    private Map<String,String> params;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

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

    public Integer getChunkId() {
        return chunkId;
    }

    public void setChunkId(Integer chunkId) {
        this.chunkId = chunkId;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getChunkPerSize() {
        return chunkPerSize;
    }

    public void setChunkPerSize(Integer chunkPerSize) {
        this.chunkPerSize = chunkPerSize;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
