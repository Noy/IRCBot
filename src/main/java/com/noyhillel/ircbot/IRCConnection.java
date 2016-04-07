package com.noyhillel.ircbot;

import com.noyhillel.ircbot.commands.CommandContext;
import com.noyhillel.ircbot.commands.CommandMap;
import lombok.Getter;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * Represents a connection to an IRC server on various channels. Is also used for delegation of events.
 */
public final class IRCConnection extends PircBot {
    @Getter private final String nickname;
    @Getter private final CommandMap commandMap;
    @Getter private static IRCConnection instance;

    IRCConnection(String nick, String server, Integer port, String... channels) throws IrcException, IOException {
        changeNick(nick);
        setName(nick);
        setAutoNickChange(true);
        connect(server, port);
        IRCConnection.instance = this;
        for (String channel : channels) {
            joinChannel(channel);
        }
        this.nickname = nick;
        this.commandMap = new CommandMap();
    }

    @Override
    protected void onMessage(String channel, String sender, String s3, String s4, String message) {
        this.commandMap.dispatchMessage(new CommandContext(sender, channel, this), message);
    }
}
