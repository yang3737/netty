package cn.lcy.wx.protocol.response;

import cn.lcy.wx.protocol.Packet;
import lombok.Data;

import static cn.lcy.wx.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
