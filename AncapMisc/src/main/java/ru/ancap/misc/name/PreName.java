package ru.ancap.misc.name;

import ru.ancap.misc.name.exception.InvalidNameException;
import ru.ancap.misc.preparable.Preparable;

public interface PreName extends Preparable {

    Name getPrepared() throws InvalidNameException;
}
