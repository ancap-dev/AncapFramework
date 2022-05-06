package ru.ancap.misc.strings.hexable;

import ru.ancap.misc.strings.AncapStringObject;

public class AncapIntegerString extends AncapStringObject implements IntegerString {

    protected static class Radix {
        public static final int DECIMAL = 10;
        public static final int HEX = 16;
    }

    public AncapIntegerString(String string) {
        super(string);
    }

    @Override
    public boolean isNumber() {
        return this.isUnderRadix(Radix.DECIMAL);
    }

    @Override
    public boolean isHexNumber() {
        return this.isUnderRadix(Radix.HEX);
    }

    @Override
    public boolean isUnderRadix(int radix) {
        try {
            Integer.parseInt(this.getString(), radix);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public int getValue() {
        return this.getValue(Radix.DECIMAL);
    }

    @Override
    public int getHexValue() {
        return this.getValue(Radix.HEX);
    }

    @Override
    public int getValue(int radix) {
        return Integer.parseInt(this.getString(), radix);
    }
}
