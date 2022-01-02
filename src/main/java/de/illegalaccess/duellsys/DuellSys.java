package de.illegalaccess.duellsys;


import de.illegalaccess.duellsys.commands.DuellCommand;
import de.illegalaccess.duellsys.listeners.InventoryClickListener;
import de.illegalaccess.duellsys.utils.DuellInventory;
import de.illegalaccess.duellsys.utils.DuellManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DuellSys extends JavaPlugin {
    public static Plugin plugin;

    DuellManager duellManager;
    DuellInventory duellInventory;
    DuellCommand duellCommand;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        duellInventory = new DuellInventory();
        duellManager = new DuellManager(duellInventory);
        duellCommand = new DuellCommand(duellManager);

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(duellManager, duellInventory), this);

        getCommand("duell").setExecutor(new DuellCommand(duellManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
