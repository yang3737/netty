package cn.lcy.wx.server;

import cn.lcy.wx.codec.PacketDecoder;
import cn.lcy.wx.codec.PacketEncoder;
import cn.lcy.wx.server.handler.AuthHandler;
import cn.lcy.wx.server.handler.LoginRequestHandler;
import cn.lcy.wx.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class NettyServer {
    private static final int PORT = 8001;
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                        .childHandler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new PacketDecoder());
                                socketChannel.pipeline().addLast(new LoginRequestHandler());
                                socketChannel.pipeline().addLast(new AuthHandler());
                                socketChannel.pipeline().addLast(new MessageRequestHandler());
                                socketChannel.pipeline().addLast(new PacketEncoder());
                            }
                        });
        bind(serverBootstrap,PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap,final int PORT){
        serverBootstrap.bind(PORT).addListener((future)->{
            if (future.isSuccess()){
                System.out.println(new Date() + ": 端口[" + PORT + "]绑定成功!");
            }else {
                System.out.println("端口[" + PORT + "]绑定失败!");
            }
        });
    }
}