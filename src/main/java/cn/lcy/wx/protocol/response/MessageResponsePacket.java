package cn.lcy.wx.protocol.response;

import cn.lcy.wx.protocol.Packet;
import lombok.Data;

import static cn.lcy.wx.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}