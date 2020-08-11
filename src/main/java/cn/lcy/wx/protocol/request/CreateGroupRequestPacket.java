package cn.lcy.wx.protocol.request;

import cn.lcy.wx.protocol.Packet;
import lombok.Data;

import java.util.List;

import static cn.lcy.wx.protocol.command.Command.CREATE_GROUP_REQUEST;


@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
