package ru.ancap.framework.plugin.event.listeners.command;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.event.OperableEvent;
import ru.ancap.framework.api.event.classic.*;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;

public class CommandEventsListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(UnknownCommandEvent event) {
        this.operateForm(
                new Form(event, event.getSender()),
                AncapPlugin.MESSAGE_DOMAIN+"unknown-command",
                new Placeholder("UNKNOWN_COMMAND", event.getUnknownValue())
        );
    }



    @EventHandler(priority = EventPriority.NORMAL)
    public void on(NotEnoughArgsEvent event) {
        this.operateForm(
                new Form(event, event.getSender()),
                AncapPlugin.MESSAGE_DOMAIN+"no-args",
                new Placeholder("COUNT", String.valueOf(event.getArgumentsLack()))
        );
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(NotEnoughPermsEvent event) {
        this.operateForm(
                new Form(event, event.getSender()),
                AncapPlugin.MESSAGE_DOMAIN+"no-perms",
                new Placeholder("PERMISSION", event.getLackedPermission())
        );
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(DescribedIncorrectArgsEvent event) {
        this.operateForm(
                new Form(event, event.getSender()),
                event.getDescription()
        );
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(IncorrectArgsEvent event) {
        this.operateForm(
                new Form(event, event.getSender()),
                AncapPlugin.MESSAGE_DOMAIN+"incorrect-args",
                new Placeholder("ARG", event.getArgument())
        );
    }

    private void operateForm(Form form, String id, Placeholder... placeholders) {
        if (form.event().operate()) {
            CommandSender sender = form.sender();
            String message = LAPI.localized(id, sender.getName());
            for (Placeholder placeholder : placeholders) {
                message = message.replace(placeholder.placeholder(), placeholder.replaceTo);
            }
            sender.sendMessage(message);
        }
    }
    private record Form(OperableEvent event, CommandSender sender) {}

    private record Placeholder(String placeholder, String replaceTo) {

        private Placeholder(String placeholder, String replaceTo) {
            this.placeholder = "%" + placeholder + "%";
            this.replaceTo = replaceTo;
        }
    }

}
