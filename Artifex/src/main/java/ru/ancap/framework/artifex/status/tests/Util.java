package ru.ancap.framework.artifex.status.tests;

import lombok.experimental.UtilityClass;
import ru.ancap.framework.artifex.Artifex;
import ru.ancap.framework.command.api.commands.object.executor.CSCommandOperator;
import ru.ancap.framework.communicate.message.CallableMessage;
import ru.ancap.framework.communicate.modifier.Placeholder;
import ru.ancap.framework.language.additional.LAPIMessage;

@UtilityClass
class Util {

    static CSCommandOperator baseResponse(String domain, short identity) {
        return dispatch -> dispatch.source().communicator().message(new LAPIMessage(
            Artifex.class, domain,
            new Placeholder("identity", identity)
        ));
    }

    static CallableMessage commandQuestion(String id, String type, String commandName, byte identity) {
        return new LAPIMessage(
            Artifex.class, "test.base."+type,
            new Placeholder("action", new LAPIMessage(
                Artifex.class, "test.checks.action.command", 
                new Placeholder("command", commandName)
            )),
            new Placeholder("expected", new LAPIMessage(
                Artifex.class, "test.checks.expected."+type+"."+id,
                new Placeholder("identity", identity)
            ))
        );
    }
    
}
