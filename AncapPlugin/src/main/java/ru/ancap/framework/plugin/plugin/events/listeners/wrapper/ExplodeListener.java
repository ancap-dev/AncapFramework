package ru.ancap.framework.plugin.plugin.events.listeners.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginManager;
import ru.ancap.framework.plugin.api.events.wrapper.AncapExplodeEvent;
import ru.ancap.framework.plugin.plugin.events.listeners.AncapListener;

public class ExplodeListener extends AncapListener {

    public ExplodeListener(PluginManager pluginManager) {
        super(pluginManager);
    }

    public ExplodeListener() {
        super();
    }

    @EventHandler (priority = EventPriority.LOW)
    public void on(EntityExplodeEvent e) {
        this.throwEvent(e, e.getLocation());
    }

    @EventHandler (priority = EventPriority.LOW)
    public void on(BlockExplodeEvent e) {
        this.throwEvent(e, e.getBlock().getLocation());
    }

    private void throwEvent(Cancellable e, Location location) {
        this.throwEvent(
                new AncapExplodeEvent(e, location)
        );
    }
}
