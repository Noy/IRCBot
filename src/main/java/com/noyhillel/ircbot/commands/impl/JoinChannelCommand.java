package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.IRCConnection;
import com.noyhillel.ircbot.commands.*;

/**
 * Created by Noy on 6/22/2014.
 */
@SenderPerm("Yes")
public final class JoinChannelCommand extends AbstractCommandHandler {
    @Override
    public String getName() {
        return "join";
    }

    @Override
    protected void handleCommand(CommandContext commandContext, String[] args) {
        if (args.length == 0) commandContext.sendMessage("Too few arguments!");
        if (!args[0].startsWith("#")) {
            commandContext.sendMessage("Channels must start with a #");
            return;
        }
        commandContext.sendMessage("Joining that channel!");
        IRCConnection.getInstance().joinChannel(args[0]);
    }
}
