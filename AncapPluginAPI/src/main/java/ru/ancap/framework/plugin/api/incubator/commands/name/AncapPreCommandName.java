package ru.ancap.framework.plugin.api.incubator.commands.name;

import ru.ancap.misc.name.AncapPreName;
import ru.ancap.misc.name.Name;
import ru.ancap.misc.name.exception.InvalidNameException;

public class AncapPreCommandName extends AncapPreName {

    public AncapPreCommandName(String string) {
        super(string);
    }

    public Name getPrepared() throws InvalidNameException {
        this.validateName();
        return new AncapCommandName(this.getString());
    }

    void validateName() throws InvalidNameException {
        if (this.getString().length() == 1 || !this.getString().matches("[a-zA-Z0-9а-яА-Я-_]+")) {
            throw new InvalidNameException();
        }
    }
}
