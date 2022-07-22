package ru.ancap.framework.plugin.command.api.implementation.catcher;

import org.bukkit.entity.Player;
import ru.ancap.framework.api.command.commands.command.dispatched.InlineTextCommand;
import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.event.CommandWrite;

public class PacketCommandWrite extends CommandWrite {

    public PacketCommandWrite(Player player, int transactionID, LeveledCommand provideCommand, InlineTextCommand packetData) {
        super(
                new PacketLineSpeaker(
                        transactionID,
                        packetData,
                        player
                ),
                provideCommand
        );
    }
}
