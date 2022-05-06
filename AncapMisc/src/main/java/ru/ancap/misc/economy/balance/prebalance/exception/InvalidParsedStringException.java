package ru.ancap.misc.economy.balance.prebalance.exception;

import ru.ancap.misc.exceptions.InvalidDataException;

public class InvalidParsedStringException extends InvalidDataException {

    public InvalidParsedStringException(String string) {
        super(string);
    }
}
