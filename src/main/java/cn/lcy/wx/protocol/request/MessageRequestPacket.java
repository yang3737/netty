package cn.lcy.wx.protocol.request;

import cn.lcy.wx.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.lcy.wx.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;
    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}