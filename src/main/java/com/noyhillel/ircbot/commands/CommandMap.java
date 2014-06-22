package com.noyhillel.ircbot.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private final static char IRC_COMMAND_PREFIX = '!';

    private final Map<String, CommandHandler> commands = new HashMap<>();

    public void registerCommand(CommandHandler commandHandler) {
        commands.put(commandHandler.getName(), commandHandler);
    }

    public void dispatchMessage(CommandContext context, String message) {
        if (message.charAt(0) != IRC_COMMAND_PREFIX) return;
        String fngm = message.substring(1, message.length());
        String[] parts = fngm.split(" ");
        if (parts.length == 0) return;
        String commandName = parts[0];
        if (!commands.containsKey(commandName)) return;
        String[] args;
        if (parts.length > 1) args = Arrays.copyOfRange(parts, 1, parts.length);
        else args = new String[]{};
        try {
            commands.get(commandName).onCommand(context, args);
        } catch (PermissionException e) {
            //TODO handle permission exception
        }
    }
}
