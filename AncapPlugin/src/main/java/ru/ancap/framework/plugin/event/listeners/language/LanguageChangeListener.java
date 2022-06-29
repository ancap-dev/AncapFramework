package ru.ancap.framework.plugin.event.listeners.language;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.plugin.event.events.command.LanguageChangeEvent;

public class LanguageChangeListener implements Listener {

    @EventHandler
    public void on(LanguageChangeEvent event) {
        LAPI.setupLanguage(event.getSender().getName(), event.getLanguage());
    }

}
