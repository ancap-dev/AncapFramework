package ru.ancap.framework.api.command.commands.command.tab;

import net.kyori.adventure.text.Component;

import java.util.Optional;

public record Tab(String completion) implements TabCompletion {

    @Override
    public Optional<Component> getTooltipState() {
        return Optional.empty();
    }
}
