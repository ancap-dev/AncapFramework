package ru.ancap.framework.plugin.plugin.configuration;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;

public class AncapLibraryConfiguration extends AncapConfiguration {

    protected static class Path {
        public static final String UUID_BASED_PLAYER_IDENTIFIER = "uuid_based_player_identifier";
    }

    public AncapLibraryConfiguration(AncapConfiguration configuration) {
        super(configuration);
    }

    public boolean isUsingUUIDPlayerIdentifier() {
        return this.getBoolean(Path.UUID_BASED_PLAYER_IDENTIFIER);
    }
}
