package tk.mybatis.springboot.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

/**
 * Created by Dong_Liu
 * date：2017/11/6
 */
public class InputStreamTest {


    /***
     * 从输入流获取字节数组,当文件很大时，报java.lang.OutOfMemoryError: Java heap space
     *
     * @since 2014-02-19
     * @param br_right
     * @param length2
     * @return
     * @throws IOException
     */
    public byte[] readBytesFromInputStream(InputStream br_right, int length2) throws IOException {
        int readSize;
        byte[] bytes = null;
        bytes = new byte[length2];

        long length_tmp = length2;
        long index = 2;// start from zero
        while ((readSize = br_right.read(bytes, (int) index, 20)) != -1) {
            length_tmp -= readSize;
            if (length_tmp == 0) {
                break;
            }
            index = index + readSize;
        }
        return bytes;
    }

    /***
     * 读取指定长度的字节
     * @since 2014-02-27
     * @param ins
     * @param sumLeng : 要读取的字节数
     * @return
     * @throws IOException
     */
    public byte[] readBytesFromGzipInputStream(InputStream ins, long sumLeng) throws IOException {
        byte[] fileNameBytes = new byte[(int) sumLeng];
        int fileNameReadLength = 0;
        int hasReadLength = 10;//已经读取的字节数
        while ((fileNameReadLength = ins.read(fileNameBytes, hasReadLength, (int) sumLeng - hasReadLength)) > 0) {
            hasReadLength = hasReadLength + fileNameReadLength;
        }
        return fileNameBytes;
    }

    /***
     * read char array from inputstream according to specified length.
     *
     * @param length2:要读取的字符总数
     * @throws IOException
     */
    public static char[] getCharsFromInputStream(BufferedReader br_right,
                                                 int length2) throws IOException {
        int readSize;
        char[] chars = null;
        chars = new char[length2];

        long length_tmp = length2;
        long index = 0;// start from zero
        while ((readSize = br_right.read(chars, (int) index, (int) length_tmp)) != -1) {
            length_tmp -= readSize;
            if (length_tmp == 0) {
                break;
            }
            index = index + readSize;// 写入字符数组的offset（偏移量）
        }
        return chars;
    }

    /***
     * 从文件中读取指定长度的字符（注意：不是字节）
     *
     * @param file
     * @param length2
     * @return
     * @throws IOException
     */
    public static char[] getCharsFromFile(File file, int length2)
            throws IOException {
        FileInputStream fin = new FileInputStream(file);
        InputStreamReader inr = new InputStreamReader(fin);
        BufferedReader br = new BufferedReader(inr);
        return getCharsFromInputStream(br, length2);
    }

    private static byte[] readDataFromLength(HttpURLConnection huc,
                                             int contentLength) throws Exception {
        InputStream in = huc.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(in);

        // 数据字节数组
        byte[] receData = new byte[contentLength];

        // 已读取的长度
        int readAlreadyLength = 0;

        while ((readAlreadyLength = readAlreadyLength
                + bis.read(receData, readAlreadyLength, contentLength
                - readAlreadyLength)) < contentLength) {
        }
        return receData;
    }

    public byte[] downFile(File file) throws IOException {
        RandomAccessFile ras=new RandomAccessFile(file, "rw");
        //默认情况下ras的指针为0，即从第1个字节读写到
        ras.seek(1);//将ras的指针设置到8，则读写ras是从第9个字节读写到
        File file2=new File("pp.txt");
        RandomAccessFile ras2=new RandomAccessFile(file2, "rw");
        ras2.setLength(10);
        ras2.seek(5);
        byte[] buffer=new byte[32];
        int len=0;
        while((len=ras.read(buffer))!=-1){
            ras2.write(buffer, 0, len);//从ras2的第6个字节被写入，因为前面设置ras2的指针为5
            //ras2的写入结果是:pp.txt的内容为前5位是空格，第6位是9
            //待写入的位置如果有内容将会被新写入的内容替换
        }
        ras.close();
        ras2.close();
        System.out.println("ok");
        return null;
    }


    public static void main(String[] args) throws IOException {
        InputStreamTest inputStreamTest = new InputStreamTest();
        InputStream in = null;
        File f = new File("D:/tt.txt");
        byte[] b = null;
        byte[] data=null;
        in = new FileInputStream(f);
        long size=20;
        b = inputStreamTest.downFile(f);
        System.out.println(Arrays.toString(b));
    }
}
