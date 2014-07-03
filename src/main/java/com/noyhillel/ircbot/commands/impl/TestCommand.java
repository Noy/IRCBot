package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.commands.*;

@SenderPerm("Yes")
public final class TestCommand extends AbstractCommandHandler {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void handleCommand(CommandContext context, String[] args) {
        if (args.length == 0) {
            context.getConnection().sendMessage(context.getChannel(), "Hello I am slim shady");
            return;
        }
        if (args[0].equalsIgnoreCase("test")) {
            context.sendMessage("hello there");
        }
    }
}