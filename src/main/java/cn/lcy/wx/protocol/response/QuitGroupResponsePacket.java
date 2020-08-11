package cn.lcy.wx.protocol.response;

import cn.lcy.wx.protocol.Packet;
import lombok.Data;

import static cn.lcy.wx.protocol.command.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
