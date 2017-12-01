package tk.mybatis.springboot.NIO;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/11/29 17:23
 * 描    述 :
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {

        SelectableChannel channel = null;
        Selector selector = Selector.open();
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            int readChannels = selector.select();
            if (readChannels == 0) {
                continue;
            }
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (selectionKey.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (selectionKey.isReadable()) {
                    // a channel is ready for reading
                } else if (selectionKey.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
