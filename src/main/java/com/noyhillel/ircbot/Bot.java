package com.noyhillel.ircbot;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import lombok.extern.java.Log;
import org.jibble.pircbot.IrcException;

import java.io.IOException;
import java.util.Scanner;

@Log
public final class Bot {
    public static void main(String[] args) throws IOException, IrcException {
        print("Starting the bot..");
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
        log.info("About to start connection thread!");
        new ConnectionRunnable(nick, server, port, channels).run();
        log.info("Started Connection thread!");
        Scanner penty = new Scanner(System.in);
        while (true) {
            System.out.println("> ");
            if (penty.nextLine().startsWith("stop")) System.exit(-1);
        }
    }

    @SafeVarargs
    public static <T> void print(T... args) {
        for(T t : args) {
            log.info(t.toString());
        }
    }
}
