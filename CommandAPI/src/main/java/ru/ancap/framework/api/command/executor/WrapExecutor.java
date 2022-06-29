package ru.ancap.framework.api.command.executor;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;

import java.util.List;

@AllArgsConstructor
public class WrapExecutor implements TabExecutor {

    private ru.ancap.framework.api.command.commands.command.executor.CommandExecutor executor;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.executor.on(new DispatchedCommand(
                sender,
                List.of(args)
        ));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return this.executor.getTabCompletionsFor(
                new DispatchedCommand(
                        sender,
                        List.of(args)
                )
        );
    }
}
