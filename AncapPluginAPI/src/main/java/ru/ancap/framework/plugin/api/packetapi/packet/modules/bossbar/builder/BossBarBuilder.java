package ru.ancap.framework.plugin.api.packetapi.packet.modules.bossbar.builder;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.bossbar.BossBar;

public interface BossBarBuilder {

    BossBarBuilder setName(String name);
    BossBarBuilder setProgress(float progress);
    BossBarBuilder setColorData(String colorData);
    BossBarBuilder setNotchesData(String notchesData);
    BossBarBuilder setDuration(int duration);

    BossBar build();
}
