package com.noyhillel.ircbot.commands;

public interface CommandHandler {

    /**
     * The command name.
     */
    String getName();

    /**
     * Handles the commands.
     */
    void onCommand(CommandContext context, String[] args) throws PermissionException;
}
