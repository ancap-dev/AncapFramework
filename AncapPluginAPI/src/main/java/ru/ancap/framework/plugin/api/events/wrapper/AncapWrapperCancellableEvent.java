package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public abstract class AncapWrapperCancellableEvent extends AncapEvent implements Cancellable {

    private Cancellable bukkitEvent;

    public AncapWrapperCancellableEvent(Cancellable event) {
        this.bukkitEvent = event;
    }

    @NotNull
    public Cancellable getBukkitEvent() {
        return this.bukkitEvent;
    }

    @Override
    public boolean isCancelled() {
        return this.bukkitEvent.isCancelled();
    }
    @Override
    public void setCancelled(boolean b) {
        this.bukkitEvent.setCancelled(b);
    }
}
