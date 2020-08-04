package cn.lcy.wx.protocol.request;

import cn.lcy.wx.protocol.command.Command;
import cn.lcy.wx.protocol.Packet;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
