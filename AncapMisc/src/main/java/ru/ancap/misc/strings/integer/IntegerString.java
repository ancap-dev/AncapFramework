package ru.ancap.misc.strings.integer;

public interface IntegerString {

    boolean isNumber();
    boolean isHexNumber();
    boolean isUnderRadix(int radix);
    int getValue();
    int getHexValue();
    int getValue(int radix);
}
