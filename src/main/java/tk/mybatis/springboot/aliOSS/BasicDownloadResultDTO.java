package tk.mybatis.springboot.aliOSS;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/28 10:47
 * 描    述 :
 */
public class BasicDownloadResultDTO extends ApiResponse<BasicFileMultipartDTO>{
    /** 下载申请的唯一编号 */
    private Integer requestId;
    /** 下载任务ID（没有作用，将被取消） */
    private String downloadId; //没有作用，将被取消
    /** 每个分片的约定大小（与chunkSize重复，将被取消） */
    private Integer chunkPerSize; //与chunkSize重复，将被取消
    /** 当前下载的分片序号 */
    private Integer chunkId;
    /** 当前下载的分片大小 */
    private Integer chunkSize;
    /** 后续内容分片数量，为0则已经下载完毕 */
    private Integer chunkCount;

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

    public Integer getChunkPerSize() {
        return chunkPerSize;
    }

    public void setChunkPerSize(Integer chunkPerSize) {
        this.chunkPerSize = chunkPerSize;
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

    public Integer getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(Integer chunkCount) {
        this.chunkCount = chunkCount;
    }
}
