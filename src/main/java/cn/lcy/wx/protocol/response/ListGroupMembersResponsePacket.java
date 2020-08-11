package cn.lcy.wx.protocol.response;

import cn.lcy.wx.protocol.Packet;
import cn.lcy.wx.session.Session;
import lombok.Data;

import java.util.List;

import static cn.lcy.wx.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
