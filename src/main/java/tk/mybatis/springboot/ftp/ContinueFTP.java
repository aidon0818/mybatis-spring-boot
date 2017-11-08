package tk.mybatis.springboot.ftp;

/**
 * Created by Dong_Liu
 * date：2017/11/1
 */

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 支持断点续传的FTP实用类
 *
 * @author BenZhou
 * @version 0.3 实现中文目录创建及中文文件创建，添加对于中文的支持
 */
public class ContinueFTP {
    public FTPClient ftpClient = new FTPClient();

    public ContinueFTP() {
        //设置将过程中使用到的命令输出到控制台
        this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
    }

    /**
     * 断开与远程服务器的连接
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.disconnect();
        }
    }

    /**
     * 连接到FTP服务器
     *
     * @param hostname 主机名
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return 是否连接成功
     * @throws IOException
     */
    public boolean connect(String hostname, int port, String username, String password) throws IOException {
        ftpClient.connect(hostname, port);
        ftpClient.setControlEncoding("GBK");
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            if (ftpClient.login(username, password)) {
                return true;
            }
        }
        disconnect();
        return false;
    }

    /**
     * 从FTP服务器上下载文件,支持断点续传，上传百分比汇报
     *
     * @param fileName 远程文件路径
     * @param local    本地文件路径
     * @return 上传的状态
     * @throws IOException
     */
    public FileStatus download(String fileName, String local) throws IOException {
        //设置被动模式
        ftpClient.enterLocalPassiveMode();
        //设置以二进制方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FileStatus result;
        //对远程目录的处理
        String remoteFileName = fileName;
        if (fileName.contains("/")) {
            remoteFileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        //检查远程文件是否存在
        FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
        if (files.length != 1) {
            System.out.println("远程文件不存在");
            return FileStatus.Remote_File_Noexist;
        }
        long lRemoteSize = files[0].getSize();
        File f = new File(local);
        //本地存在文件，进行断点下载
        if (f.exists()) {
            long localSize = f.length();
            //判断本地文件大小是否大于远程文件大小
            if (localSize >= lRemoteSize) {
                System.out.println("本地文件大于远程文件，下载中止");
                return FileStatus.Local_Bigger_Remote;
            }

            //进行断点续传，并记录状态
            FileOutputStream out = new FileOutputStream(f, true);
            ftpClient.setRestartOffset(localSize);
            InputStream in = ftpClient.retrieveFileStream(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
            byte[] bytes = new byte[1024];
            long step = lRemoteSize / 100;
            long process = localSize / step;
            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
                localSize += c;
                long nowProcess = localSize / step;
                if (nowProcess > process) {
                    process = nowProcess;
                    if (process % 10 == 0) {
                        System.out.println("下载进度：" + process);
                    }
                    //TODO 更新文件下载进度,值存放在process变量中
                }
            }
            in.close();
            out.close();
            boolean isDo = ftpClient.completePendingCommand();
            if (isDo) {
                result = FileStatus.Download_From_Break_Success;
            } else {
                result = FileStatus.Download_From_Break_Failed;
            }
        } else {
            OutputStream out = new FileOutputStream(f);
            InputStream in = ftpClient.retrieveFileStream(new String(fileName.getBytes("GBK"), "iso-8859-1"));
            byte[] bytes = new byte[1024];
            long step = lRemoteSize / 100;
            long process = 0;
            long localSize = 0L;
            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
                localSize += c;
                long nowProcess = localSize / step;
                if (nowProcess > process) {
                    process = nowProcess;
                    if (process % 10 == 0) {
                        System.out.println("下载进度：" + process);
                    }
                    //TODO 更新文件下载进度,值存放在process变量中
                }
            }
            in.close();
            out.close();
            boolean upNewStatus = ftpClient.completePendingCommand();
            if (upNewStatus) {
                result = FileStatus.Download_New_Success;
            } else {
                result = FileStatus.Download_New_Failed;
            }
        }
        return result;
    }

    /**
     * 上传文件到FTP服务器，支持断点续传
     *
     * @param local    本地文件名称，绝对路径
     * @param fileName 远程文件路径，使用/home/directory1/subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @param
     * @return 上传结果
     * @throws IOException
     */
    public FileStatus upload(String local, String fileName) throws IOException {
        //设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        //设置以二进制流的方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding("GBK");
        FileStatus result;
        //对远程目录的处理
        String remoteFileName = fileName;
        if (fileName.contains("/")) {
            remoteFileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            //创建服务器远程目录结构，创建失败直接返回
            if (CreateDirecroty(fileName, ftpClient) == FileStatus.Create_Directory_Fail) {
                return FileStatus.Create_Directory_Fail;
            }
        }

        //检查远程是否存在文件
        FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
        if (files.length == 1) {
            long remoteSize = files[0].getSize();
            File f = new File(local);
            long localSize = f.length();
            if (remoteSize == localSize) {
                return FileStatus.File_Exist;
            } else if (remoteSize > localSize) {
                return FileStatus.Remote_Bigger_Local;
            }

            //尝试移动文件内读取指针,实现断点续传
            result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

            //如果断点续传没有成功，则删除服务器上文件，重新上传
            if (result == FileStatus.Upload_From_Break_Failed) {
                if (!ftpClient.deleteFile(remoteFileName)) {
                    return FileStatus.Delete_Remote_Faild;
                }
                result = uploadFile(remoteFileName, f, ftpClient, 0);
            }
        } else {
            result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
        }
        return result;
    }

    /**
     * 递归创建远程服务器目录
     *
     * @param remote    远程服务器文件绝对路径
     * @param ftpClient FTPClient对象
     * @return 目录创建是否成功
     * @throws IOException
     */
    public FileStatus CreateDirecroty(String remote, FTPClient ftpClient) throws IOException {
        FileStatus status = FileStatus.Create_Directory_Success;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"), "iso-8859-1"))) {
            //如果远程目录不存在，则递归创建远程服务器目录
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录失败");
                        return FileStatus.Create_Directory_Fail;
                    }
                }

                start = end + 1;
                end = directory.indexOf("/", start);

                //检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return status;
    }

    /**
     * 上传文件到服务器,新上传和断点续传
     *
     * @param remoteFile 远程文件名，在上传之前已经将服务器工作目录做了改变
     * @param localFile  本地文件File句柄，绝对路径
     * @param ftpClient  FTPClient引用
     * @return
     * @throws IOException
     */
    public FileStatus uploadFile(String remoteFile, File localFile, FTPClient ftpClient, long remoteSize) throws IOException {
        FileStatus status;
        //显示进度的上传
        long step = localFile.length() / 100;
        long process = 0;
        long localreadbytes = 0L;
        RandomAccessFile raf = new RandomAccessFile(localFile, "r");
        OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes("GBK"), "iso-8859-1"));
        //断点续传
        if (remoteSize > 0) {
            ftpClient.setRestartOffset(remoteSize);
            process = remoteSize / step;
            raf.seek(remoteSize);
            localreadbytes = remoteSize;
        }
        byte[] bytes = new byte[1024];
        int c;
        while ((c = raf.read(bytes)) != -1) {
            out.write(bytes, 0, c);
            localreadbytes += c;
            if (0 == step) {
                System.out.println("上传进度:100%");
            } else if (localreadbytes / step != process) {
                process = localreadbytes / step;
                System.out.println("上传进度:" + process);
                //TODO 汇报上传状态
            }
        }
        out.flush();
        raf.close();
        out.close();
        boolean result = ftpClient.completePendingCommand();
        if (remoteSize > 0) {
            status = result ? FileStatus.Upload_From_Break_Success : FileStatus.Upload_From_Break_Failed;
        } else {
            status = result ? FileStatus.Upload_New_File_Success : FileStatus.Upload_New_File_Failed;
        }
        return status;
    }

    /**
     * Description: 从FTP服务器删除文件
     *
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public FileStatus delFile(String localPath, String fileName) throws IOException {
        try {
            //设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            //设置以二进制流的方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("GBK");
            FileStatus result;
            //对远程目录的处理
            String remoteFileName = fileName;
            if (fileName.contains("/")) {
                remoteFileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            }
            //检查远程文件是否存在
            FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
            if (files.length != 1 || !remoteFileName.equals(files[0].getName())) {
                return FileStatus.File_Nonexistent;
            }
            boolean deleted = ftpClient.deleteFile(remoteFileName);
            if (deleted) {
                return FileStatus.Delete_File_Success;
            } else {
                return FileStatus.File_Nonexistent;
            }
        } catch (IOException e) {
            return FileStatus.Delete_File_Failed;
        }
    }

    /**
     * Description: 从FTP服务器查询文件
     *
     * @param fileName  要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public List<String> selFile(String fileName, String localPath) {
        boolean success = false;
        List list = new ArrayList();
        FTPFile[] files;
        String[] s = {"a", "b", "c"};
        try {
            files = ftpClient.listFiles(localPath);
            list = Arrays.asList(files);
            for (FTPFile f : files) {
                if (!f.getName().equals(".") && !f.getName().equals("..")) {
                    System.out.print(f.getName() + "\t");
                    System.out.println(ftpClient.getModificationTime(f.getName()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return list;
    }

    public Boolean isExist(String fileName, String localPath) {
        FTPFile[] files;
        boolean status = false;
        try {
            //切换目录
            ftpClient.changeWorkingDirectory(new String(localPath.getBytes("GBK"), "iso-8859-1"));
            files = ftpClient.listFiles(fileName);
            if (0 < files.length) {
                status = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return status;
    }

    /**
     * 文件重命名
     *
     * @throws IOException
     */
    public boolean rename(String fileName, String localPath, String newFileName) {
        boolean result = false;
        try {
            result = ftpClient.rename(fileName, newFileName);//重命名远程文件
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 复制文件.
     *
     * @param sourceFileName
     * @throws IOException
     */
    public boolean copyFile(String sourceFileName, String sourceDir, String targetDir) throws IOException {
        InputStream is = null;
        boolean result = false;
        try {
            ftpClient.enterLocalPassiveMode();
            // 变更工作路径
            ftpClient.changeWorkingDirectory(sourceDir);
            // 设置以二进制流的方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            is = ftpClient.retrieveFileStream(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"));
            // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
//            ftpClient.getReply();
            if (is != null) {
//                ftpClient.changeWorkingDirectory(targetDir);
//                result = ftpClient.storeFile(new String("rt.txt".getBytes("GBK"), "iso-8859-1"), is);
                result = ftpClient.storeFile(new String("t.rar".getBytes("GBK"), "iso-8859-1"), is);
                return result;
            }

        } finally {
            // 关闭流
            if (is != null) {
                is.close();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ContinueFTP myFtp = new ContinueFTP();
        boolean result = false;
        try {
            myFtp.connect("192.168.33.148", 2121, "administrator", "30116992");
//          myFtp.ftpClient.makeDirectory(new String("歌曲".getBytes("GBK"),"iso-8859-1"));
            //可以多层目录
//            myFtp.ftpClient.changeWorkingDirectory(new String("/歌曲".getBytes("GBK"), "iso-8859-1"));
//          myFtp.ftpClient.makeDirectory(new String("爱你等于爱自己".getBytes("GBK"),"iso-8859-1"));
            System.out.println(myFtp.upload("C:\\22.rar", "/测2试23456.rar"));
//          System.out.println(myFtp.upload("E:\\爱你等于爱自己.mp4","/爱你等于爱自己.mp4"));
//          System.out.println(myFtp.download("/22.rar", "C:\\58955.rar"));
//            System.out.println(myFtp.delFile("", "/aaa.txt"));
//            List list = myFtp.selFile("", "");
//            boolean sta = myFtp.isExist("test1.txt", "");
//            result= myFtp.copyFile("tt.rar", "", "");
//            result= myFtp.copyFile("re.txt", "", "");
            System.out.println(result);
            myFtp.disconnect();
        } catch (IOException e) {
            System.out.println("连接FTP出错：" + e.getMessage());
        }
    }
}
