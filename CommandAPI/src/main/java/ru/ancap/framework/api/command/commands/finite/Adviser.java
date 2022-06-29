package ru.ancap.framework.api.command.commands.finite;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;

import java.util.List;

@AllArgsConstructor
public class Adviser implements CommandExecutor {

    private AdviseProvider provider;

    @Override
    public void on(DispatchedCommand command) {
        command.getSender().sendMessage(this.provider.advice(command.getSender()));
    }

    @Override
    public List<String> getTabCompletionsFor(DispatchedCommand command) {
        return List.of(this.provider.advice(command.getSender()));
    }

    public interface AdviseProvider {

        String advice(CommandSender sender);

    }
}
