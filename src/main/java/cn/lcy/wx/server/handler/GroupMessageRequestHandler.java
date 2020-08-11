package cn.lcy.wx.server.handler;

import cn.lcy.wx.protocol.request.GroupMessageRequestPacket;
import cn.lcy.wx.protocol.response.GroupMessageResponsePacket;
import cn.lcy.wx.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        String groupId = requestPacket.getToGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));
        channelGroup.writeAndFlush(responsePacket);
    }
}
