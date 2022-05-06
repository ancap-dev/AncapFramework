package ru.ancap.misc.name;

public abstract class AncapPreName implements PreName {

    private String string;

    public AncapPreName(String string) {
        this.string = string;
    }

    public AncapPreName(AncapPreName name) {
        this(name.getString());
    }

    protected String getString() {
        return this.string;
    }
}
