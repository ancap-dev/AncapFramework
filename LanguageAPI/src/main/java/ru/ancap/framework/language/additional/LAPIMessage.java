package ru.ancap.framework.language.additional;

import ru.ancap.communicate.message.CallableMessage;
import ru.ancap.communicate.message.Message;
import ru.ancap.communicate.message.WrapperMessage;
import ru.ancap.communicate.replacement.Replacement;
import ru.ancap.framework.language.LAPI;

public class LAPIMessage extends WrapperMessage implements CallableMessage {
    
    public LAPIMessage(String id, Replacement... replacements) {
        super(nameIdentifier -> new Message(LAPI.localized(id, nameIdentifier), replacements).call(nameIdentifier));
    }

    public LAPIMessage(Class<?> class_, String id, Replacement... replacements) {
        this(LAPIDomain.of(class_, id), replacements);
    }
    
}