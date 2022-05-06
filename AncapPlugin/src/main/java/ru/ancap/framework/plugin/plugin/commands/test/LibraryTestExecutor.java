package ru.ancap.framework.plugin.plugin.commands.test;

import ru.ancap.framework.plugin.api.configuration.language.Language;
import ru.ancap.framework.plugin.api.incubator.commands.AncapCommand;
import ru.ancap.framework.plugin.api.incubator.commands.AncapCommandExecutor;
import ru.ancap.framework.plugin.api.incubator.commands.AncapConsoleCommand;
import ru.ancap.framework.plugin.api.packetapi.AncapPacketReceiver;
import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;
import ru.ancap.framework.plugin.plugin.AncapLibrary;

import java.util.List;

public class LibraryTestExecutor extends AncapCommandExecutor {

    private AncapLibrary library;

    public LibraryTestExecutor(String operatedCommandName, AncapLibrary library) {
        super(operatedCommandName);
        this.library = library;
    }

    @Override
    public void onPlayerCommand(AncapCommand command) {
        this.testPacketApiAndConfiguration(command);
    }

    @Override
    public void onConsoleCommand(AncapConsoleCommand ancapConsoleCommand) {
        ancapConsoleCommand.getSender().sendMessage("not supported");
    }

    private void testPacketApiAndConfiguration(AncapCommand command) {
        PacketReceiver receiver = new AncapPacketReceiver(command.getSender());
        Language mainLanguage = library.getSettings().getMainLanguage();
        Language first = (Language) library.getSettings().getAlternateLanguages().toArray()[0];
        receiver.send(library.getMessageConfiguration()
                .getLanguage(mainLanguage)
                .getNoPermissionPacket("test")
        );
        receiver.send(library.getMessageConfiguration()
                .getLanguage(mainLanguage)
                .getInvalidArgPacket("test", List.of("test1", "test2"))
        );
        receiver.send(library.getMessageConfiguration()
                .getLanguage(mainLanguage)
                .getInvalidArgsCountPacket(2, 10)
        );
        receiver.send(library.getMessageConfiguration()
                .getLanguage(first)
                .getNoPermissionPacket("test")
        );
        receiver.send(library.getMessageConfiguration()
                .getLanguage(first)
                .getInvalidArgPacket("test", List.of("test1", "test2"))
        );
        receiver.send(library.getMessageConfiguration()
                .getLanguage(first)
                .getInvalidArgsCountPacket(2, 10)
        );
    }
}
