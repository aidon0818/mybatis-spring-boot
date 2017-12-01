package tk.mybatis.springboot.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/30 15:31
 * 描    述 :DatagramChannel是一个能收发UDP包的通道
 */
public class DatagramChannelTest {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.connect(new InetSocketAddress("www.baidu.com", 80));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);
        System.out.println(channel);

    }
}
