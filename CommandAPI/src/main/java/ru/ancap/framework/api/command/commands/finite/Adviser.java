package ru.ancap.framework.api.command.commands.finite;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import ru.ancap.framework.api.command.commands.command.event.CommandDispatch;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;

@AllArgsConstructor
public class Adviser implements CommandOperator {

    private AdviseProvider provider;

    @Override
    public void on(CommandDispatch dispatch) {
        dispatch.sender().sendMessage(this.provider.advice(dispatch.sender()));
    }

    public interface AdviseProvider {

        String advice(CommandSender sender);

    }
}
