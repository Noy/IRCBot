package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.commands.CommandContext;
import com.noyhillel.ircbot.commands.CommandHandler;
import com.noyhillel.ircbot.commands.PermissionException;

public final class TestCommand implements CommandHandler {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void onCommand(CommandContext context, String[] args) throws PermissionException {
        context.getConnection().sendMessage(context.getChannel(), "Hello I am slim shady");
    }
}
