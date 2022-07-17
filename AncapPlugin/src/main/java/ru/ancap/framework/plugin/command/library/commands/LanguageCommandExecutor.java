package ru.ancap.framework.plugin.command.library.commands;

import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.command.commands.CommandTarget;
import ru.ancap.framework.api.command.commands.delegator.CommandDelegator;
import ru.ancap.framework.api.command.commands.delegator.subcommand.Raw;
import ru.ancap.framework.api.command.commands.delegator.subcommand.SubCommand;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.StringDelegatePattern;
import ru.ancap.framework.api.command.commands.finite.Adviser;
import ru.ancap.framework.api.command.commands.finite.FiniteCommandTarget;
import ru.ancap.framework.api.command.commands.finite.pattern.SingleArgumenter;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;
import ru.ancap.framework.plugin.event.events.command.LanguageChangeEvent;

import java.util.List;

public class LanguageCommandExecutor extends CommandTarget {

    public LanguageCommandExecutor() {
        super(
                new CommandDelegator(
                        new Raw(
                                new Adviser(sender -> LAPI.localized(AncapPlugin.MESSAGE_DOMAIN+"enter-language", sender.getName()))
                        ),
                        new SubCommand(
                                new StringDelegatePattern("set"),
                                new FiniteCommandTarget(
                                        new SingleArgumenter(LanguageChangeEvent :: new),
                                        write -> write.getSpeaker().sendTabs(List.of(
                                                "ru",
                                                "en",
                                                "uk"
                                        ))
                                )
                        )
                )
        );
    }

}
