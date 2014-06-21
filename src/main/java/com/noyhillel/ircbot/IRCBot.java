package com.noyhillel.ircbot;

import com.noyhillel.ircbot.commands.TestCommand;

/**
 * Created by Noy on 6/21/2014.
 */
public class IRCBot {

    public static void main(String[] args) throws Exception {
        TestCommand testCommand = new TestCommand();
        testCommand.setVerbose(true);
    }
}
