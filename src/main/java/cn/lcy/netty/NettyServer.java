package cn.lcy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 闪电侠
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nch){
                        nch.pipeline().addLast(new StringDecoder());
                        nch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                        System.out.println(s);
                                    }
                                });
                    }
                }).bind(8000);
    }
}