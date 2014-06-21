package com.noyhillel.ircbot.messaging;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * Created by Noy on 6/21/2014.
 */
public abstract class AbstractIRCBot extends PircBot {

    private String command;

    public AbstractIRCBot(String name, String server, Integer port, String channel, String command) throws IrcException, IOException {
        this.setName(name);
        this.connect(server, port);
        this.joinChan(channel);
        this.command = command;
    }

    protected abstract void onCommand(String channel, String sender, String login, String hostname);

    @Override
    protected final void onMessage(String s, String s2, String s3, String s4, String s5) {
        this.command = s5;
        onCommand(s, s2, s3, s4);
    }

    @Override
    protected final void onOp(String s, String s2, String s3, String s4, String s5) {
        onOpUser(s,s2,s3,s4,s5);
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
}
