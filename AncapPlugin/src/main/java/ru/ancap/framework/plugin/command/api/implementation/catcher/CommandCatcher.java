package ru.ancap.framework.plugin.command.api.implementation.catcher;

import com.github.retrooper.packetevents.event.CancellableEvent;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientTabComplete;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import ru.ancap.framework.api.command.commands.command.dispatched.InlineTextCommand;
import ru.ancap.framework.api.command.commands.command.dispatched.TextCommand;
import ru.ancap.framework.api.command.commands.command.event.CommandDispatch;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.operate.OperateRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public class CommandCatcher implements Listener, PacketListener {

    private final CommandOperator global;
    private final OperateRule rule;

    @EventHandler
    public void on(PlayerCommandPreprocessEvent event) {
        this.operateDispatch(
                event,
                event.getPlayer(), event.getMessage(),
                this :: operateDispatch
        );
    }

    @EventHandler
    public void on(ServerCommandEvent event) {
        this.operateDispatch(
                event,
                event.getSender(), event.getCommand(),
                this :: operateDispatch
        );
    }

     private void operateDispatch(EventCommandForm form) {
        this.global.on(
                new CommandDispatch(
                        form.sender,
                        form.command
                )
        );
        form.event.setCancelled(true);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() != PacketType.Play.Client.TAB_COMPLETE) return;
        if (event.isCancelled()) return;
        WrapperPlayClientTabComplete packet = new WrapperPlayClientTabComplete(event);
        InlineTextCommand inlineTextCommand = new InlineTextCommand(packet.getText());
        if (!this.rule.isOperate(inlineTextCommand)) return;
        if (inlineTextCommand.isRaw()) return;
        event.setCancelled(true);
        this.global.on(
                new PacketCommandWrite(
                        packet,
                        (Player) event.getPlayer(),
                        inlineTextCommand
                )
        );
    }

    private void operateDispatch(Cancellable event, CommandSender sender, String sent, Consumer<EventCommandForm> consumer) {
        if (event.isCancelled()) return;
        TextCommand command = this.from(sender, sent);
        if (!this.rule.isOperate(command)) return;
        consumer.accept(new EventCommandForm(event, sender, command));
    }

    private TextCommand from(CommandSender sender, String message) {
        return this.from(
                new RawForm(sender, message)
        );
    }

    private TextCommand from(RawForm form) {
        return this.from(new Form(
                form.sender,
                new ArrayList<>(Arrays.asList(form.command.split(" ")))
                        .stream()
                        .skip(1)
                        .toList()
        ));
    }

    private TextCommand from(Form form) {
        return new TextCommand(
                form.arguments
        );
    }

    private record RawForm(CommandSender sender, String command) {}

    private record Form(CommandSender sender, List<String> arguments) {}

    private record EventCommandForm(Cancellable event, CommandSender sender, TextCommand command) {}

    private record CancellableAbstraction(@Delegate CancellableEvent event) implements Cancellable {

    }

}
