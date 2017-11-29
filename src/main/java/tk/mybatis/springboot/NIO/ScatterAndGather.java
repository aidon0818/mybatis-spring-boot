package tk.mybatis.springboot.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/29 15:36
 * 描    述 :Scatter/Gather
 */
public class ScatterAndGather {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel = null;
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body   = ByteBuffer.allocate(1024);
        ByteBuffer[] bufferArray = { header, body };
//        注意buffer首先被插入到数组，然后再将数组作为channel.read() 的输入参数。
//        read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，
//        当一个buffer被写满后，channel紧接着向另一个buffer中写。
        inChannel.read(bufferArray);
//        buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，
//        将数据写入到channel，注意只有position和limit之间的数据才会被写入。
//        因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。
//        因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。
        inChannel.write(bufferArray);
    }
}
