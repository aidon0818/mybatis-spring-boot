package tk.mybatis.springboot.ftp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

/**
 * Created by Dong_Liu
 * date：2017/11/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpServiceTest {
    @Autowired
    private FtpService ftpService;

    @Test
    public void uploadFile() throws Exception {
        FileInputStream in = new FileInputStream(new File("c:/2.txt"));
        boolean flag = ftpService.uploadFile("192.168.33.148", 2121, "administrator", "30116992", "", "3.txt", in);
        System.out.println(flag);
    }

    @Test
    public void downFile() throws Exception {
        boolean flag = ftpService.downFile("192.168.33.148", 2121, "administrator", "30116992", "", "3.txt", "");
        System.out.println(flag);
    }

    @Test
    public void delFile() throws Exception {
        boolean flag = ftpService.delFile("192.168.33.148", 2121, "administrator", "30116992", "", "1.txt", "");
        System.out.println(flag);
    }

    @Test
    public void selFile() throws Exception {
        boolean flag = ftpService.selFile("192.168.33.148", 2121, "administrator", "30116992", "", "1.txt", "");
        System.out.println(flag);
    }

}