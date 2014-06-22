package com.noyhillel.ircbot.messaging;

import com.noyhillel.ircbot.commands.Command;
import com.noyhillel.ircbot.commands.Permission;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * Created by Noy on 6/21/2014.
 */
public abstract class AbstractCommandHandler extends PircBot implements Permission {

    private final String name;

    private String permissionMessage;

    public AbstractCommandHandler(String name, String server, Integer port, String channel, String permName) throws IrcException, IOException {
        this.setName(name);
        this.connect(server, port);
        this.joinChan(channel);
        this.name = permName;
        this.permissionMessage = printPermissionMessage();
    }

    protected abstract String printPermissionMessage();

    protected abstract void onCommand(String channel, String sender, String login, String hostname);

    @Override
    protected final void onMessage(String channel, String sender, String login, String hostname, String message) {
        hasPermission(channel, sender, message);
    }

    @Override
    public String permissionName() {
        return this.name;
    }

    @Override
    public String permissionMessage() {
        return permissionMessage;
    }

    @Override
    protected final void onOp(String s, String s2, String s3, String s4, String s5) {
        onOpUser(s, s2, s3, s4, s5);
    }

    private boolean hasPermission(String channel, String sender, String msg) {
        Command annotation = getClass().getAnnotation(Command.class);
        if (msg.equalsIgnoreCase("!" + annotation.value())) {
            if (sender.equalsIgnoreCase(permissionName())) {
                onCommand(channel, sender, null, null);
                return true;
            } else {
                sendMessage(channel, permissionMessage);
                return true;
            }
        }
        return true;
    }

    protected final void kill(String channel, String msg) {
        this.sendMsg(channel, msg);
        System.exit(-1);
    }

    protected final void sendMsg(String channel, String msg) {
        this.sendMessage(channel, msg);
    }

    protected final void joinChan(String channel) {
        this.joinChannel(channel);
    }

    protected final void onUserJoin(String channel, String sender, String login, String hostname) {
        this.onJoin(channel, sender, login, hostname);
    }


    protected final void onNicknameChange(String oldNick, String login, String hostname, String newNick) {
        this.onNickChange(oldNick, login, hostname, newNick);
    }

    protected void onOpUser(String s, String s2, String s3, String s4, String s5) {

    }

    protected final void setTopicForChannel(String channel, String topic) {
        this.setTopic(channel, topic);
    }

    protected void onUserQuit(String channel, String sender, String login, String hostname) {
        super.onQuit(channel, sender, login, hostname);
    }
}