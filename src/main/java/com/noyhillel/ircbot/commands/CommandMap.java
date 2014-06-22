package com.noyhillel.ircbot.commands;

import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log
public class CommandMap {
    private final static Character IRC_COMMAND_PREFIX = '!';

    private final Map<String, CommandHandler> commands = new HashMap<>();

    public void registerCommand(CommandHandler commandHandler) {
        log.info("Registered command " + commandHandler .getName());
        commands.put(commandHandler.getName(), commandHandler);
    }

    public void dispatchMessage(CommandContext context, String message) {
        if (message.charAt(0) != IRC_COMMAND_PREFIX) return;
        String substring = message.substring(1, message.length());
        String[] parts = substring.split(" ");
        if (parts.length == 0) return;
        String commandName = parts[0];
        if (!commands.containsKey(commandName)) return;
        String[] args;
        if (parts.length > 1) args = Arrays.copyOfRange(parts, 1, parts.length);
        else args = new String[]{};
        try {
            commands.get(commandName).onCommand(context, args);
        } catch (PermissionException e) {
            context.sendMessage(context.getSender() + ", you do not have permission to perform this command");
        }
    }
}
