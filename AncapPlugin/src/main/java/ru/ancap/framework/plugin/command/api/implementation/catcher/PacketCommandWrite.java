package ru.ancap.framework.plugin.command.api.implementation.catcher;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientTabComplete;
import org.bukkit.entity.Player;
import ru.ancap.framework.api.command.commands.command.dispatched.InlineTextCommand;
import ru.ancap.framework.api.command.commands.command.event.CommandWrite;

public class PacketCommandWrite extends CommandWrite {

    public PacketCommandWrite(WrapperPlayClientTabComplete packet, Player player, InlineTextCommand inlineTextCommand) {
        super(
                new PacketLineSpeaker(
                        packet.getTransactionId().orElse(0),
                        inlineTextCommand,
                        player
                ),
                inlineTextCommand
        );
    }
}
