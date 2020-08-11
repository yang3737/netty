package cn.lcy.wx.protocol.response;

import cn.lcy.wx.protocol.Packet;
import cn.lcy.wx.session.Session;
import lombok.Data;

import static cn.lcy.wx.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
