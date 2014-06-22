package com.noyhillel.ircbot;

import com.noyhillel.ircbot.commands.impl.TestCommand;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.jibble.pircbot.IrcException;

import java.io.IOException;

public class Bot {
    public static void main(String[] args) throws IOException, IrcException {
        OptionParser optionParser = new OptionParser();
        ArgumentAcceptingOptionSpec<String> nickOption = optionParser.accepts("nick").withRequiredArg();
        ArgumentAcceptingOptionSpec<String> channelsOption = optionParser.accepts("channels").withRequiredArg();
        ArgumentAcceptingOptionSpec<String> serverOption = optionParser.accepts("server").withRequiredArg();
        ArgumentAcceptingOptionSpec<String> portOption = optionParser.accepts("port").withOptionalArg();
        OptionSet parse = optionParser.parse(args);
        String nick = parse.valueOf(nickOption);
        String[] channels = parse.valueOf(channelsOption).split(",");
        String server = parse.valueOf(serverOption);
        Integer port;
        if (!(parse.has(portOption) && parse.hasArgument(portOption))) port = 6667;
        else {
            try {
                port = Integer.valueOf(parse.valueOf(portOption));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid port specified");
            }
        }
        IRCConnection connection = new IRCConnection(nick, server, port, channels);
        //TODO register commands
        connection.getCommandMap().registerCommand(new TestCommand());
    }
}
