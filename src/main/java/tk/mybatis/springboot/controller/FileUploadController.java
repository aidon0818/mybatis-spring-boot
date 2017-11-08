package tk.mybatis.springboot.controller;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;


/**
 * Created by Dong_Liu
 * date：2017/10/23
 * 图片上传
 */
@Slf4j
@Controller
public class FileUploadController {
    //跳转到上传文件的页面
    @RequestMapping(value = "/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    /**
     * 上传文件
     * @param file 文件流类
     * @param request 请求
     * @throws IOException IO异常
     */
    @RequestMapping(value = "/testuploadimg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        File dirPath = new File(filePath);
        try {
            File uploadFile = new File(dirPath + "/" + fileName);
            FileCopyUtils.copy(file.getBytes(), uploadFile);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "uploadimg success";
    }
    /**
     * 复制文件
     * @param localFileName 原文件地址和文件名
     * @param tmpFileName 目标文件地址和文件名
     * @throws IOException
     */
    public static void copyFile(String localFileName,String tmpFilePath,String tmpFileName) throws IOException{
        File localFile = new File(localFileName);
        File tmpFile = new File(tmpFilePath,tmpFileName);
        FileCopyUtils.copy(localFile, tmpFile);
    }
    /**
     * 删除文件
     * @param fileName 文件地址和文件名
     * @throws IOException
     */
    public static void deleteFile(String fileName) throws IOException{
        File localFile = new File(fileName);
        localFile.delete();
    }

}
