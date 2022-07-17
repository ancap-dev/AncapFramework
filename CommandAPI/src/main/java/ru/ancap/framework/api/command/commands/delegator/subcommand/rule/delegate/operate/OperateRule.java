package ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.operate;

import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;

public interface OperateRule {

    boolean isOperate(LeveledCommand command);

}
