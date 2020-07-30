package cn.lcy.wx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端开始登录");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         ByteBuf byteBuf = (ByteBuf) msg;
         Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
         if (packet instanceof LoginResponsePacket){
             LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
             if (responsePacket.isSuccess()) {
                 System.out.println(new Date() + ": 客户端登录成功");
                 LoginUtil.markAsLogin(ctx.channel());
             } else {
                 System.out.println(new Date() + ": 客户端登录失败，原因：" + responsePacket.getReason());
             }
         }else if(packet instanceof MessageResponsePacket){
             MessageResponsePacket messageRequestPacket = (MessageResponsePacket) packet;
             System.out.println(new Date() + ": 收到服务端的消息: " + messageRequestPacket.getMessage());
         }
    }
}
