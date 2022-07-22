package ru.ancap.framework.api.command.commands.command.executor.conversation;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.ancap.framework.api.command.commands.command.tab.TabCompletion;

import java.util.List;

public interface CommandLineSpeaker extends CommandSender {

    Player getPerson();

    void sendTabs(List<String> tabs);

    void sendTooltipTabs(List<TabCompletion> tabs);

    default void sendTooltip(Component tooltip) {
        throw new UnsupportedOperationException();
    }

    default void setLine(Component line) {
        throw new UnsupportedOperationException();
    }
}
