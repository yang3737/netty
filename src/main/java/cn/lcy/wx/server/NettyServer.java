package cn.lcy.wx.server;

import cn.lcy.wx.codec.PacketDecoder;
import cn.lcy.wx.codec.PacketEncoder;
import cn.lcy.wx.codec.Spliter;
import cn.lcy.wx.protocol.request.LogoutRequestHandler;
import cn.lcy.wx.server.handler.*;
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
                            protected void initChannel(NioSocketChannel ch) {
                                ch.pipeline().addLast(new Spliter());
                                ch.pipeline().addLast(new PacketDecoder());
                                // 登录请求处理器
                                ch.pipeline().addLast(new LoginRequestHandler());
                                ch.pipeline().addLast(new AuthHandler());
                                // 单聊消息请求处理器
                                ch.pipeline().addLast(new MessageRequestHandler());
                                // 创建群请求处理器
                                ch.pipeline().addLast(new CreateGroupRequestHandler());
                                // 加群请求处理器
                                ch.pipeline().addLast(new JoinGroupRequestHandler());
                                // 退群请求处理器
                                ch.pipeline().addLast(new QuitGroupRequestHandler());
                                // 获取群成员请求处理器
                                ch.pipeline().addLast(new ListGroupMembersRequestHandler());

                                ch.pipeline().addLast(new GroupMessageRequestHandler());
                                // 登出请求处理器
                                ch.pipeline().addLast(new LogoutRequestHandler());
                                ch.pipeline().addLast(new PacketEncoder());
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