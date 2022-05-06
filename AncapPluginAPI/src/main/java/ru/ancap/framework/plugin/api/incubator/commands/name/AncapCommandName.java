package ru.ancap.framework.plugin.api.incubator.commands.name;

import ru.ancap.misc.name.Name;

public class AncapCommandName implements Name {

    private String name;

    AncapCommandName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
