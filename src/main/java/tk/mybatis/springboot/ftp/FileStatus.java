package tk.mybatis.springboot.ftp;

/**
 * Created by Dong_Liu
 * date：2017/11/2
 */
public enum FileStatus {
    Create_Directory_Fail,      //远程服务器相应目录创建失败
    Create_Directory_Success,   //远程服务器闯将目录成功
    Upload_New_File_Success,    //上传新文件成功
    Upload_New_File_Failed,     //上传新文件失败
    File_Exist,                 //文件已经存在
    File_Nonexistent,           //文件不存在
    Remote_Bigger_Local,        //远程文件大于本地文件
    Upload_From_Break_Success,  //断点续传成功
    Upload_From_Break_Failed,   //断点续传失败

    Remote_File_Noexist,    //远程文件不存在
    Local_Bigger_Remote,    //本地文件大于远程文件
    Download_From_Break_Success,    //断点下载文件成功
    Download_From_Break_Failed,     //断点下载文件失败
    Download_New_Success,           //全新下载文件成功
    Download_New_Failed,            //全新下载文件失败


    Delete_Remote_Faild,        //删除远程文件失败
    Delete_File_Success,            //删除文件成功
    Delete_File_Failed;           //删除文件失败
}
