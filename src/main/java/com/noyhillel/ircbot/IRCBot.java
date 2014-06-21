package com.noyhillel.ircbot;

import org.jibble.pircbot.IrcException;

import java.io.IOException;

/**
 * Created by Noy on 6/21/2014.
 */
public class IRCBot extends AbstractIRCBot {


    protected IRCBot() throws IOException, IrcException {
        super("ChrisThePirillo", "irc.geekshed.net", 6667, "#testing");
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, String message) {
        if (message.equalsIgnoreCase("Test")) {
            for (Integer s = 0; s <= 50; s++) {
                sendMsg(channel, "FOR LOOPS RULE");
            }
        }
    }

    @Override
    protected void onOpUser(String s, String s2, String s3, String s4, String s5) {
        sendMessage(s, "FUCK");
    }
}
