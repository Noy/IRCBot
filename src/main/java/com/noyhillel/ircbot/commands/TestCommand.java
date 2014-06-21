package com.noyhillel.ircbot.commands;

import com.noyhillel.ircbot.messaging.AbstractIRCBot;
import org.jibble.pircbot.IrcException;

import java.io.IOException;

/**
 * Created by Noy on 6/21/2014.
 */
public class TestCommand extends AbstractIRCBot {


    public TestCommand() throws IOException, IrcException {
        super("ChrisThePirillo", "irc.geekshed.net", 6667, "#testing", "test");
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname) {
        for (Integer s = 0; s <= 50; s++) {
            sendMsg(channel, "FOR LOOPS RULE");
        }
    }
}
