package ru.ancap.framework.util.location;

import org.bukkit.Location;

public class BlockLocation {
    
    public static boolean blockEquals(Location one, Location other) {
        return one.getWorld().getName().equals(other.getWorld().getName()) &&
            one.getBlockX() == other.getBlockX() &&
            one.getBlockY() == other.getBlockY() &&
            one.getBlockZ() == other.getBlockZ();
    }
    
}