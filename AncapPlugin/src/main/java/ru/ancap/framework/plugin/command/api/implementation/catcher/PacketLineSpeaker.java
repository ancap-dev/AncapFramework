package ru.ancap.framework.plugin.command.api.implementation.catcher;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTabComplete;
import lombok.experimental.Delegate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.ancap.framework.api.command.commands.command.TabCompletion;
import ru.ancap.framework.api.command.commands.command.dispatched.InlineTextCommand;
import ru.ancap.framework.api.command.commands.command.executor.conversation.CommandLineSpeaker;

import java.util.List;

public class PacketLineSpeaker implements CommandLineSpeaker {

    @Delegate
    private final CommandSender sender;
    private final Player player;
    private final Integer transactionID;
    private final InlineTextCommand command;

    public PacketLineSpeaker(Integer transactionID, InlineTextCommand command, Player player) {
        this.transactionID = transactionID;
        this.player = player;
        this.sender = player;
        this.command = command;
    }

    @Override
    public Player getPerson() {
        return player;
    }

    @Override
    public void sendTabs(List<String> tabs) {
        tabs = tabs.stream()
                .filter(s -> s.startsWith(command.getHotArgument()))
                .toList();
        this.sendTab(
                tabs.stream()
                        .map(WrapperPlayServerTabComplete.CommandMatch :: new)
                        .toList()
        );
    }


    @Override
    public void sendTooltipTabs(List<TabCompletion> tabs) {
        tabs = tabs.stream()
                .filter(s -> s.completion().startsWith(command.getHotArgument()))
                .toList();
        this.sendTab(
                tabs.stream()
                        .map(tabCompletion -> new WrapperPlayServerTabComplete.CommandMatch(
                                tabCompletion.completion(),
                                tabCompletion.tooltip()
                        ))
                        .toList()
        );
    }

    private void sendTab(List<WrapperPlayServerTabComplete.CommandMatch> matches) {
        WrapperPlayServerTabComplete complete = new WrapperPlayServerTabComplete(
                transactionID,
                new WrapperPlayServerTabComplete.CommandRange(command.hotArgumentStart(), command.hotArgumentEnd()),
                matches
        );
        PacketEvents.getAPI().getPlayerManager().sendPacketSilently(player, complete);
    }
}
