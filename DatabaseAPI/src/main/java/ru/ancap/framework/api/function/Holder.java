package ru.ancap.framework.api.function;

import java.util.function.Supplier;

public interface Holder<T> {

    T get(Supplier<T> initializer);

}
