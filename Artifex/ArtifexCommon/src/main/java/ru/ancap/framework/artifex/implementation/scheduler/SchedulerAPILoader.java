package ru.ancap.framework.artifex.implementation.scheduler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.communicate.communicator.Communicator;
import ru.ancap.framework.communicate.modifier.Placeholder;
import ru.ancap.framework.language.additional.LAPIMessage;
import ru.ancap.framework.database.sql.SQLDatabase;
import ru.ancap.framework.database.sql.connection.data.DatabaseLocation;
import ru.ancap.framework.runtime.Artifex;
import ru.ancap.scheduler.Scheduler;
import ru.ancap.scheduler.StableScheduler;
import ru.ancap.scheduler.support.ScheduleSupport;
import ru.ancap.scheduler.support.StableScheduleSupport;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

@AllArgsConstructor
@ToString @EqualsAndHashCode
public class SchedulerAPILoader implements Runnable {
    
    private final Communicator uiOutput;
    private final JavaPlugin owner;
    private final Scanner uiInput;
    private final SQLDatabase database;
    private final SchedulerSilencer schedulerSilencer;
    
    private final BiConsumer<Scheduler, ScheduleSupport> outputConsumer;
    
    @Override
    public void run() {
        if (this.database.info().databaseLocation() != DatabaseLocation.LOCAL) {
            throw new RuntimeException("SchedulerAPI should use local database!");
        }
        
        StableScheduler scheduler = new StableScheduler(
                this.database.dataSource(),
                Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors())
        );
        
        scheduler.load((uuid, class_) -> {
            if (this.schedulerSilencer.isSilenced(uuid)) return;
            
            this.uiOutput.message(new LAPIMessage(
                    Artifex.class, "console.scheduler-api.task-cannot-be-loaded",
                    new Placeholder("task class", class_),
                    new Placeholder("plugin", "undefined")
            ));
            int code = this.scanCode();
            
            this.uiOutput.message(new LAPIMessage(Artifex.class, "console.scheduler-api.executing-chosen-task", new Placeholder("number", code)));
            if (code == 1) scheduler.cancel(uuid);
            if (code == 2) this.schedulerSilencer.upsert(new SilencedTask(uuid, true, this.owner.getName()));
            if (code == 3) /* nothing */ ;
            if (code == 4) Bukkit.shutdown();
            this.uiOutput.message(new LAPIMessage(Artifex.class, "console.scheduler-api.chosen-task-executed"));
        });
        
        ScheduleSupport scheduleSupport = new StableScheduleSupport(this.database.dataSource()).load();
        
        this.outputConsumer.accept(scheduler, scheduleSupport);
    }
    
    private int scanCode() {
        int code = this.uiInput.nextInt();
        if (code != 1 && code != 2 && code != 3 && code != 4) {
            this.uiOutput.message(new LAPIMessage(Artifex.class, "console.scheduler-api.incorrect-task", new Placeholder("number", code)));
            return this.scanCode();
        }
        return code;
    }
    
}