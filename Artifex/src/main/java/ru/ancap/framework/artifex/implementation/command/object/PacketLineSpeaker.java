package ru.ancap.framework.artifex.implementation.command.object;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTabComplete;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.apiguardian.api.API;
import org.bukkit.entity.Player;
import ru.ancap.framework.command.api.commands.object.conversation.CommandLineSpeaker;
import ru.ancap.framework.command.api.commands.object.conversation.CommandSource;
import ru.ancap.framework.command.api.commands.object.dispatched.InlineTextCommand;
import ru.ancap.framework.command.api.commands.object.tab.TabBundle;

import java.util.List;
import java.util.stream.Collectors;

@ToString @EqualsAndHashCode
public class PacketLineSpeaker implements CommandLineSpeaker {
    
    @API(status = API.Status.STABLE)
    public static void sendTabPacket(Player player, int transactionID, InlineTextCommand command, TabBundle tab) {
        WrapperPlayServerTabComplete.CommandRange commandRange = new WrapperPlayServerTabComplete.CommandRange(
            command.argumentStart(tab.argumentsToReplace()) + 1,
            command.argumentsEnd() + 1
        );
        
        List<WrapperPlayServerTabComplete.CommandMatch> commandMatches = tab.tabCompletions().stream()
            .map(ancapCompletion -> new WrapperPlayServerTabComplete.CommandMatch(
                ancapCompletion.completion(),
                ancapCompletion.tooltipState().orElse(null)
            ))
            .toList();
        
        WrapperPlayServerTabComplete packet = new WrapperPlayServerTabComplete(transactionID, commandRange, commandMatches);
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);
    }
    
    private final int transactionID;
    private final CommandSource source;
    private final Player player;
    private final InlineTextCommand command;
    
    public PacketLineSpeaker(int transactionID, InlineTextCommand command, Player player) {
        this.transactionID = transactionID;
        this.player = player;
        this.source = new SenderSource(player);
        this.command = command;
    }
    
    @Override
    public void sendTab(@NonNull TabBundle tab) {
        if (tab.filter()) tab = tab.withTabCompletions(tab.tabCompletions().stream()
            .filter(s -> s.completion().startsWith(this.command.getHotArgument()))
            .collect(Collectors.toList())
        );
        
        PacketLineSpeaker.sendTabPacket(this.player, this.transactionID, this.command, tab);
    }
    
    @Override
    public CommandSource source() {
        return this.source;
    }
    
}