package cn.lcy.wx.server.handler;

import cn.lcy.wx.protocol.request.MessageRequestPacket;
import cn.lcy.wx.protocol.response.MessageResponsePacket;
import cn.lcy.wx.session.Session;
import cn.lcy.wx.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage(messageRequestPacket.getMessage());
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());

        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if (toUserChannel!=null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(responsePacket);
        }
    }

}
