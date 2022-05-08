package ru.ancap.framework.plugin.plugin.configuration;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;
import ru.ancap.framework.plugin.api.configuration.extended.ExtendedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.coreplugin.CoreConfiguration;
import ru.ancap.misc.placeholder.PlaceholderSource;

import java.util.List;

public class AncapLibraryMessageConfiguration extends AncapConfiguration implements CoreConfiguration {

    protected static class Placeholder {
        public static final String INVALID = "INVALID";
        public static final String ACCEPTED = "ACCEPTED";
        public static final String PERMISSION = "PERMISSION";
    }

    protected static class Path {
        public static final String INVALID_ARGS_COUNT = "invalid_args_count";
        public static final String INVALID_ARG = "invalid_arg";
        public static final String NO_PERMS = "no_perms";
    }

    protected static class Delimiter {
        public static final String COMMA = " ,";
    }

    public AncapLibraryMessageConfiguration(AncapConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Sendable getInvalidArgsCountPacket(Integer invalidCount, Integer acceptedCount) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.INVALID, invalidCount)
                .addPlaceholder(Placeholder.ACCEPTED, acceptedCount)
                .build();
        ExtendedConfigurationSection section = this.getConfigurationSection(Path.INVALID_ARGS_COUNT);
        section.setPlaceholderSource(source);
        try {
            return section.getPacket();
        } catch (InvalidConfigurationSendableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sendable getInvalidArgPacket(String invalid, List<String> accepted) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.INVALID, invalid)
                .addPlaceholder(Placeholder.ACCEPTED, accepted, Delimiter.COMMA)
                .build();
        ExtendedConfigurationSection section = this.getConfigurationSection(Path.INVALID_ARG);
        section.setPlaceholderSource(source);
        try {
            return section.getPacket();
        } catch (InvalidConfigurationSendableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sendable getNoPermissionPacket(String perm) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.PERMISSION, perm)
                .build();
        ExtendedConfigurationSection section = this.getConfigurationSection(Path.NO_PERMS);
        section.setPlaceholderSource(source);
        try {
            return section.getPacket();
        } catch (InvalidConfigurationSendableException e) {
            throw new RuntimeException(e);
        }
    }

}
