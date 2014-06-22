package com.noyhillel.ircbot.commands;

public interface CommandHandler {
    String getName();
    void onCommand(CommandContext context, String[] args) throws PermissionException;
}
