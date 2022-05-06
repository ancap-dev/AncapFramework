package ru.ancap.framework.plugin.plugin.events.listeners.wrapper;

import org.bukkit.event.EventHandler;
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

    @EventHandler
    public void explodeEvent(EntityExplodeEvent e) {
        this.throwEvent(
                new AncapExplodeEvent(e)
        );
    }

    @EventHandler
    public void explodeEvent(BlockExplodeEvent e) {
        this.throwEvent(
                new AncapExplodeEvent(e)
        );
    }
}
