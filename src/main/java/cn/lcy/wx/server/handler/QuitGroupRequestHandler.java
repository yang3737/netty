package cn.lcy.wx.server.handler;

import cn.lcy.wx.protocol.request.QuitGroupRequestPacket;
import cn.lcy.wx.protocol.response.QuitGroupResponsePacket;
import cn.lcy.wx.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        ChannelGroup group = SessionUtil.getChannelGroup(quitGroupRequestPacket.getGroupId());
        group.remove(channelHandlerContext.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(quitGroupRequestPacket.getGroupId());
        responsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
