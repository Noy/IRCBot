package com.noyhillel.ircbot.commands;

public abstract class AbstractCommandHandler implements CommandHandler {

    @Override
    public final void onCommand(CommandContext context, String[] args) throws PermissionException {
        SenderPerm annotation = getClass().getAnnotation(SenderPerm.class);
        if (!context.getSender().equalsIgnoreCase(annotation.value())) throw new PermissionException();
        handleCommand(context, args);
    }

    protected void handleCommand(CommandContext commandContext, String[] args) {}

}
