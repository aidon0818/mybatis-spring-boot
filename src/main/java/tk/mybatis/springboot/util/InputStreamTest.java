package tk.mybatis.springboot.util;

import java.io.*;

/**
 * Created by Dong_Liu
 * date：2017/11/6
 */
public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        File f = new File("D:/test.txt");
        byte[] b = new byte[1024];

        in = new FileInputStream(f);
        int i = 0;
        while ((i = in.read(b)) != -1) {
            String str = new String(b);
            System.out.print(str);
        }
    }
}
