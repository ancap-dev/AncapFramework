package ru.ancap.framework.plugin.api.incubator.commands;

public abstract class AbstractHandleableCommand extends AncapCommand implements Handleable {

    public AbstractHandleableCommand(AncapCommand command) {
        super(command);
    }
}
