package ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.AncapBossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;

public class BossBarBuilderImpl implements BossBarBuilder {

    private String name;
    private float progress;
    private String colorData;
    private String notchesData;
    private int duration;


    @Override
    public BossBarBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public BossBarBuilder setProgress(float progress) {
        this.progress = progress;
        return this;
    }

    @Override
    public BossBarBuilder setColorData(String colorData) {
        this.colorData = colorData;
        return this;
    }

    @Override
    public BossBarBuilder setNotchesData(String notchesData) {
        this.notchesData = notchesData;
        return this;
    }

    @Override
    public BossBarBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public BossBar build() {
        return new AncapBossBar(
                this.name,
                this.progress,
                this.colorData,
                this.notchesData,
                this.duration
        );
    }
}
