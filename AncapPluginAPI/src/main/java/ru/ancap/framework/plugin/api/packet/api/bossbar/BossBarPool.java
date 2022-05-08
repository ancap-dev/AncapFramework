package ru.ancap.framework.plugin.api.packet.api.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.bossbar.exception.NoSuchBossBarException;

import java.util.HashMap;
import java.util.Map;

public class BossBarPool {

    private static Map<String, BossBar> bossBarMap = new HashMap<>();

    public static void poll(String name, BossBar bossBar) {
        bossBarMap.put(name.toUpperCase(), bossBar);
    }

    public static BossBar find(String name) throws NoSuchBossBarException {
        BossBar bossBar = bossBarMap.get(name.toUpperCase());
        if (bossBar == null) {
            throw new NoSuchBossBarException();
        }
        return bossBar;
    }
}
