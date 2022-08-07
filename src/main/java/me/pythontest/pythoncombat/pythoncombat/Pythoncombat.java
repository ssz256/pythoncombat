package me.pythontest.pythoncombat.pythoncombat;

import me.pythontest.pythoncombat.events.EventsListener;
import me.pythontest.pythoncombat.managers.CommandManager;
import me.pythontest.pythoncombat.managers.StorageManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pythoncombat extends JavaPlugin {
    private StorageManager storageManager = new StorageManager(this);
    private CommandManager commandManager = new CommandManager(this);
    private EventsListener eventsListener = new EventsListener(this);
    @Override
    public void onEnable() {
        commandManager.registercommands();
        this.getServer().getPluginManager().registerEvents(eventsListener,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public StorageManager getStorageManager(){
        return this.storageManager;
    }
}
