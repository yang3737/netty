package cn.lcy.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {
    public static final String GREETING = "Hello I must be going.\r\n";

    public static void main(String[] argv)
            throws Exception {
        int port = 1234;
        if (argv.length > 0) {
            port = Integer.valueOf(argv[0]);
        }
        //创建一个缓冲区对象
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
        //建立一个ServerScocket通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定监听端口
        ssc.socket().bind(new InetSocketAddress(port));
        //设置为非阻塞模式
        ssc.configureBlocking(false);
        while (true) {
            System.out.println("等待连接");
            //是否有客户连接
            SocketChannel sc = ssc.accept();
            if (sc == null) {
                Thread.sleep(5000);
            } else {
                sc.configureBlocking(false);
                //翻转缓冲;
                while (buffer.remaining() > 0) {
                    byte a = buffer.get();
                    System.out.println(a);
                }
                buffer.clear();

                System.out.println("Incoming connection from: "
                        + sc.socket().getRemoteSocketAddress());
                buffer.rewind();

                sc.close();
            }
        }

    }
}