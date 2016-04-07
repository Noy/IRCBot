package com.noyhillel.ircbot.commands;

interface CommandHandler {

    /**
     * The command name.
     */
    String getName();

    /**
     * Handles the commands.
     */
    void onCommand(CommandContext context, String[] args) throws PermissionException;
}
