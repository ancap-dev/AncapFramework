package ru.ancap.framework.plugin.api.incubator.commands;

public interface CommandExecutor {

    void register();
    void onPlayerCommand(AncapCommand command);
    void onConsoleCommand(AncapConsoleCommand ancapConsoleCommand);
}
