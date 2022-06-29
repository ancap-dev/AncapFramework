package ru.ancap.framework.api.command.commands.command.executor;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Упрощённый CommandExecutor - вместо массива лист, удалён ненужный Label и Command, сразу же добавлен таб комплитер
 */
public interface CommandExecutor {

    void on(DispatchedCommand command);

    default List<String> getTabCompletionsFor(DispatchedCommand command) {
        return new ArrayList<>();
    }

}
