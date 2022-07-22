package ru.ancap.framework.api.function;

import java.util.function.Supplier;

public class InitializedHolder<T> implements Holder<T> {

    private T holtValue;

    @Override
    public T get(Supplier<T> initializer) {
        if (holtValue == null) {
            holtValue = initializer.get();
        }
        return holtValue;
    }
}
