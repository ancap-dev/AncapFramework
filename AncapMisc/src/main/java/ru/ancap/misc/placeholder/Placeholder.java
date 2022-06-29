package ru.ancap.misc.placeholder;

public class Placeholder {

    private final String name;

    public Placeholder(String name) {
        this.name = name;
    }

    public Placeholder(Placeholder placeholder) {
        this.name = placeholder.getName();
    }

    protected String name() {
        return this.name;
    }

    public String getName() {
        return this.name.toUpperCase();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
