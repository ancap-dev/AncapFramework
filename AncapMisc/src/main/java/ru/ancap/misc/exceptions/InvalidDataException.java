package ru.ancap.misc.exceptions;

public class InvalidDataException extends RuntimeException {

    private String invalidData;

    public InvalidDataException(String string) {
        this.invalidData = string;
    }

    public String getInvalidData() {
        return this.invalidData;
    }

}
