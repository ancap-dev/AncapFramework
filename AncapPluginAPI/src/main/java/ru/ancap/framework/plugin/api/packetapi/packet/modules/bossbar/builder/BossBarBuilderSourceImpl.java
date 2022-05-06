package ru.ancap.framework.plugin.api.packetapi.packet.modules.bossbar.builder;

public class BossBarBuilderSourceImpl implements BossBarBuilderSource {

    @Override
    public BossBarBuilder getBossBarBuilder() {
        return new BossBarBuilderImpl();
    }
}
