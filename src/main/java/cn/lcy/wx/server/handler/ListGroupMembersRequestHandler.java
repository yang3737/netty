package cn.lcy.wx.server.handler;

import cn.lcy.wx.protocol.request.ListGroupMembersRequestPacket;
import cn.lcy.wx.protocol.response.ListGroupMembersResponsePacket;
import cn.lcy.wx.session.Session;
import cn.lcy.wx.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket requestPacket) throws Exception {
       String groupId = requestPacket.getGroupId();
       ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
