package ru.ancap.framework.artifex.implementation.plugin;

import lombok.AllArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.communicate.Communicator;
import ru.ancap.communicate.message.CallableMessage;
import ru.ancap.communicate.message.Message;
import ru.ancap.communicate.replacement.Placeholder;
import ru.ancap.framework.artifex.Artifex;
import ru.ancap.framework.command.api.commands.object.event.CommandDispatch;
import ru.ancap.framework.command.api.commands.object.executor.CommandOperator;
import ru.ancap.framework.language.additional.LAPIDomain;
import ru.ancap.framework.language.additional.LAPIMessage;

@AllArgsConstructor
public class AuthorsSupplier implements CommandOperator {
    
    private final JavaPlugin plugin;
    private final String domain;

    public AuthorsSupplier(JavaPlugin plugin) {
        this(plugin, LAPIDomain.of(Artifex.class, "plugin-info"));
    }

    @Override
    public void on(CommandDispatch dispatch) {
        new Communicator(dispatch.source().sender()).send(new LAPIMessage(
                this.domain,
                new Placeholder("version", new Message(this.plugin.getDescription().getVersion())),
                new Placeholder("authors", this.plugin.getDescription().getAuthors().stream().reduce((s1, s2) -> s1+", "+s2)
                        .map(authors -> (CallableMessage) new Message(authors))
                        .orElse(new LAPIMessage("literals.nobody"))
                )
        ));
    }
}