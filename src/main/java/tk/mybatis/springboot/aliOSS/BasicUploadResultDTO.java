package tk.mybatis.springboot.aliOSS;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 10:47
 * 描    述 :
 */
public class BasicUploadResultDTO extends ApiResponse<BasicFileDTO>{
    /** 上传申请的唯一编号 */
    private Integer requestId;
    /** 上传任务ID */
    private String uploadId;
    /** 当前上传的分片序号 */
    private Integer chunkId;
    /** 实际上传的分片大小 */
    private Integer chunkSize;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
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
}
