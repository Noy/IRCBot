package com.noyhillel.ircbot.commands.impl;

import com.noyhillel.ircbot.commands.*;

@SenderPerm("Yes")
public final class TestCommand extends AbstractCommandHandler implements CommandHandler {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void handleCommand(CommandContext context, String[] args) {
        context.getConnection().sendMessage(context.getChannel(), "Hello I am slim shady");
    }
}
