package ru.ancap.misc.preparable;

import ru.ancap.misc.preparable.exception.PreparationException;

public interface Preparable {

    Object getPrepared() throws PreparationException;

}
