package ru.ancap.framework.plugin.event.listeners.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.command.commands.finite.pattern.IncorrectArgsEvent;
import ru.ancap.framework.api.event.OperableEvent;
import ru.ancap.framework.api.event.classic.DescribedIncorrectArgsEvent;
import ru.ancap.framework.api.event.classic.NotEnoughArgsEvent;
import ru.ancap.framework.api.event.classic.NotEnoughPermsEvent;
import ru.ancap.framework.api.event.classic.UnknownCommandEvent;
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
        if (form.getEvent().operate()) {
            CommandSender sender = form.getSender();
            String message = LAPI.localized(id, sender.getName());
            for (Placeholder placeholder : placeholders) {
                message = message.replace(placeholder.getPlaceholder(), placeholder.replaceTo);
            }
            sender.sendMessage(message);
        }
    }

    @AllArgsConstructor
    @Getter
    private static class Form {

        private final OperableEvent event;
        private final CommandSender sender;

    }

    @Getter
    private static class Placeholder {

        private final String placeholder;
        private final String replaceTo;


        public Placeholder(String placeholder, String replaceTo) {
            this.placeholder = "%"+placeholder+"%";
            this.replaceTo = replaceTo;
        }
    }
}
