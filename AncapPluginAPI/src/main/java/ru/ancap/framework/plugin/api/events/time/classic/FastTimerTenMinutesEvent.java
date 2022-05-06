package ru.ancap.framework.plugin.api.events.time.classic;

import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.events.time.AncapTimerEvent;

public class FastTimerTenMinutesEvent extends AncapTimerEvent {

    public static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
