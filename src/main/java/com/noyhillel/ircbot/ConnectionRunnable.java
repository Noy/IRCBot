package com.noyhillel.ircbot;

import com.noyhillel.ircbot.commands.impl.JoinChannelCommand;
import com.noyhillel.ircbot.commands.impl.TestCommand;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.util.Arrays;

@Data
@Log
public final class ConnectionRunnable implements Runnable {
    private final String nick;
    private final String server;
    private final Integer port;
    private final String[] channels;

    @Setter(AccessLevel.NONE) private IRCConnection connection;

    @Override
    @SneakyThrows
    public void run() {
        log.info("Connecting to " + server + ":" + port + " on " + Arrays.toString(channels) + " as " + nick);
        connection = new IRCConnection(nick, server, port, channels);
        connection.getCommandMap().registerCommand(new TestCommand());
        connection.getCommandMap().registerCommand(new JoinChannelCommand());
    }
}
