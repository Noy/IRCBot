package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.IRCConnection;
import com.noyhillel.ircbot.commands.AbstractCommandHandler;
import com.noyhillel.ircbot.commands.CommandContext;
import com.noyhillel.ircbot.commands.SenderPerm;

/**
 * Created by Noy on 03/07/2014.
 */
@SenderPerm("Yes")
public final class KickCommand extends AbstractCommandHandler {

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    protected void handleCommand(CommandContext commandContext, String[] args) {
        if (args.length == 0) {
            commandContext.sendMessage("changing nick!");
            return;
        }
        commandContext.sendMessage("Kicking User!");
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        IRCConnection.getInstance().kick(commandContext.getChannel(), args[0], builder.toString().trim());
    }
}
