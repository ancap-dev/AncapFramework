package ru.ancap.framework.plugin.plugin.configuration;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
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
    public Packet getInvalidArgsCountPacket(Integer invalidCount, Integer acceptedCount) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.INVALID, invalidCount)
                .addPlaceholder(Placeholder.ACCEPTED, acceptedCount)
                .build();
        return this.getPacket(AncapLibraryMessageConfiguration.Path.INVALID_ARGS_COUNT, source);
    }

    @Override
    public Packet getInvalidArgPacket(String invalid, List<String> accepted) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.INVALID, invalid)
                .addPlaceholder(Placeholder.ACCEPTED, accepted, Delimiter.COMMA)
                .build();
        return this.getPacket(Path.INVALID_ARG, source);
    }

    @Override
    public Packet getNoPermissionPacket(String perm) {
        PlaceholderSource source = this.getNewSourceBuilder()
                .addPlaceholder(Placeholder.PERMISSION, perm)
                .build();
        return this.getPacket(Path.NO_PERMS, source);
    }

}
