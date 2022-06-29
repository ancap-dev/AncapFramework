package ru.ancap.framework.plugin.heartbeat;

import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.plugin.heartbeat.exceptions.AncapHeartbeatAlreadyStartedException;

public class AncapHeartbeat {

    private static volatile boolean started = false;

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
        if (AncapHeartbeat.started) {
            throw new AncapHeartbeatAlreadyStartedException("AncapHeartbeat is already started");
        }
    }

    private void loadRunnable() {
        AncapHeartbeatRunnable runnable = new AncapHeartbeatRunnable();
        runnable.runTaskTimer(this.getOwner(), 0, 20L);
    }

    private void finishLoading() {
        AncapHeartbeat.setStarted();
    }

    private static synchronized void setStarted() {
        AncapHeartbeat.started = true;
    }

}
