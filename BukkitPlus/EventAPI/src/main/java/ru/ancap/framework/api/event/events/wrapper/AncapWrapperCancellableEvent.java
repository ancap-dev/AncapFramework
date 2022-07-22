package ru.ancap.framework.api.event.events.wrapper;

import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public abstract class AncapWrapperCancellableEvent extends AncapEvent implements Cancellable {

    private final Cancellable bukkitEvent;

    public AncapWrapperCancellableEvent(Cancellable event) {
        super(false);
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
