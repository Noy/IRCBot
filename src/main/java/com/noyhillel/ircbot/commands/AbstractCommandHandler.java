package com.noyhillel.ircbot.commands;

/**
 * Created by Noy on 6/22/2014.
 */
public abstract class AbstractCommandHandler implements CommandHandler {

    @Override
    public final void onCommand(CommandContext context, String[] args) throws PermissionException {
        if (!context.getSender().equalsIgnoreCase("Yes")) throw new PermissionException();
        handleCommand(context, args);
    }

    protected void handleCommand(CommandContext commandContext, String[] args) {}

}
