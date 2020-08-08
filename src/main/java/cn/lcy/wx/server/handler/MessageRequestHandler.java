package cn.lcy.wx.server.handler;

import cn.lcy.wx.protocol.request.MessageRequestPacket;
import cn.lcy.wx.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复："+messageRequestPacket.getMessage());
        channelHandlerContext.channel().writeAndFlush(messageRequestPacket);
    }

}
