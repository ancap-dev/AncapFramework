package ru.ancap.misc.strings;

public abstract class AncapStringObject implements StringObject {

    private String string;

    public AncapStringObject(String string) {
        this.string = string;
    }

    public AncapStringObject(AncapStringObject object) {
        this(object.getString());
    }

    public String getString() {
        return this.string;
    }

    public void replace(String placeholder, String value) {
        this.string = this.string.replace(placeholder, value);
    }
}
