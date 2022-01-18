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

    private DuellManager duellManager;
    private DuellInventory duellInventory;
    private DuellCommand duellCommand;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.duellInventory = new DuellInventory();
        this.duellManager = new DuellManager(this.duellInventory);
        this.duellCommand = new DuellCommand(this.duellManager);

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(duellManager, duellInventory), this);

        getCommand("duell").setExecutor(new DuellCommand(duellManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
