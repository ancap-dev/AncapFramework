package ru.ancap.framework.plugin.plugin.events.timer.heartbeat;

import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.plugin.plugin.events.timer.heartbeat.Exceptions.AncapHeartbeatAlreadyStartedException;

public class AncapHeartbeat {

    private static boolean STARTED = false;

    private final JavaPlugin owner;

    public AncapHeartbeat(JavaPlugin owner) {
        this.owner = owner;
    }

    public AncapHeartbeat(AncapHeartbeat heartbeat) {
        this(heartbeat.getOwner());
    }

    protected JavaPlugin getOwner() {
        return this.owner;
    }

    public void start() {
        this.validateStart();
        this.loadRunnable();
        this.finishLoading();
    }

    private void validateStart() {
        if (AncapHeartbeat.STARTED) {
            throw new AncapHeartbeatAlreadyStartedException("AncapHeartbeat is already started");
        }
    }

    private void loadRunnable() {
        AncapHeartbeatRunnable runnable = new AncapHeartbeatRunnable();
        runnable.runTaskTimer(this.getOwner(), 0, 20L);
    }

    private void finishLoading() {
        AncapHeartbeat.STARTED = true;
    }

}
