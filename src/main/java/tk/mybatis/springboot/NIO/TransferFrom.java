package tk.mybatis.springboot.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/29 15:52
 * 描    述 :通道之间的数据传输
 * 只会替换相对应的字符
 */
public class TransferFrom {
    public static void main(String[] args) {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
