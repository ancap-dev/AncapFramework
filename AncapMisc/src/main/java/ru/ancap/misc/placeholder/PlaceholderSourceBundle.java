package ru.ancap.misc.placeholder;

import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.List;

public class PlaceholderSourceBundle implements PlaceholderSource {

    private List<PlaceholderSource> sources;

    public PlaceholderSourceBundle(List<PlaceholderSource> sources) {
        this.sources = sources;
    }

    public PlaceholderSourceBundle(PlaceholderSourceBundle bundle) {
        this(bundle.getSources());
    }

    protected List<PlaceholderSource> getSources() {
        return sources;
    }

    @Override
    public String getValue(Placeholder placeholder) throws NoSuchPlaceholderException {
        for (PlaceholderSource source : sources) {
            try {
                return source.getValue(placeholder);
            } catch (NoSuchPlaceholderException ignored) {
            }
        }
        throw new NoSuchPlaceholderException(placeholder);
    }
}
