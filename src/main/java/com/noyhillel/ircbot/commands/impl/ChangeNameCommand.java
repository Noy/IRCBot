package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.IRCConnection;
import com.noyhillel.ircbot.commands.AbstractCommandHandler;
import com.noyhillel.ircbot.commands.CommandContext;
import com.noyhillel.ircbot.commands.SenderPerm;

/**
 * Created by N on 11/21/2015.
 */
@SenderPerm("Yes")
public final class ChangeNameCommand extends AbstractCommandHandler {

    @Override
    public String getName() {
        return "setname";
    }

    @Override
    protected void handleCommand(CommandContext commandContext, String[] args) {
        if (args.length == 0) commandContext.sendMessage("Too few arguments!");
        IRCConnection.getInstance().changeNick(args[0]);
        commandContext.sendMessage("Changed name!");
    }
}