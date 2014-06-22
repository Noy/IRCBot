package com.noyhillel.ircbot.commands;

import com.noyhillel.ircbot.messaging.AbstractIRCBot;
import org.jibble.pircbot.IrcException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noy on 6/21/2014.
 */
@Command("hello")
public class TestCommand extends AbstractIRCBot {

    public TestCommand() throws IOException, IrcException {
        super("ChrisThePirillo", "irc.geekshed.net", 6667, "#testing", "Yes");
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname) {
        sendMsg(channel, "test");
    }

    @Override
    protected String printPermissionMessage() {
        return "You do not have permission!";
    }
}