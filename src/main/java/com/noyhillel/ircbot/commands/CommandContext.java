package com.noyhillel.ircbot.commands;

import com.noyhillel.ircbot.IRCConnection;
import lombok.Value;

@Value
public class CommandContext {
    private final String sender;
    private final String channel;
    private final IRCConnection connection;
    public void sendMessage(String message) {
        connection.sendMessage(channel, message);
    }
}
