package ru.ancap.misc.placeholder;

public class PlaceholderSourceBuilderSourceImpl implements PlaceholderSourceBuilderSource {

    @Override
    public PlaceholderSourceBuilder getPlaceholderSourceBuilder() {
        return new PlaceholderSourceBuilderImpl();
    }
}
