package ru.ancap.util.communicate;

import java.util.Map;

public class MiniMessageMapper {
    
    private static final Map<String, String> fromLegacyMap = Map.ofEntries(
            Map.entry("§0", "<reset><black>"),
            Map.entry("§1", "<reset><dark_blue>"),
            Map.entry("§2", "<reset><dark_green>"),
            Map.entry("§3", "<reset><dark_aqua>"),
            Map.entry("§4", "<reset><dark_red>"),
            Map.entry("§5", "<reset><dark_purple>"),
            Map.entry("§6", "<reset><gold>"),
            Map.entry("§7", "<reset><gray>"),
            Map.entry("§8", "<reset><dark_gray>"),
            Map.entry("§9", "<reset><blue>"),
            Map.entry("§a", "<reset><green>"),
            Map.entry("§b", "<reset><aqua>"),
            Map.entry("§c", "<reset><red>"),
            Map.entry("§d", "<reset><light_purple>"),
            Map.entry("§e", "<reset><yellow>"),
            Map.entry("§f", "<reset><white>"),
            Map.entry("§k", "<obfuscated>"),
            Map.entry("§l", "<bold>"),
            Map.entry("§m", "<strikethrough>"),
            Map.entry("§n", "<underlined>"),
            Map.entry("§o", "<italic>"),
            Map.entry("§r", "<reset>")
    );


    public static String mapLegacy(String translated) {
        for (String key : fromLegacyMap.keySet()) {
            translated = translated.replace(key, fromLegacyMap.get(key));
        }
        return translated;
    }
}