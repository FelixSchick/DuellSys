package de.illegalaccess.duellsys.listeners;

import de.illegalaccess.duellsys.utils.DuellInventory;
import de.illegalaccess.duellsys.utils.DuellManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    private DuellManager duellManager;
    private DuellInventory duellInventory;
    public InventoryClickListener(DuellManager getDuellManager, DuellInventory getDuellInventory) {
        duellManager = getDuellManager;
        duellInventory = getDuellInventory;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§7» §bDuell")) {
            Player player = (Player) event.getWhoClicked();
            if (duellManager.duellPlayer.containsKey(player) || duellManager.duellPlayer.containsValue(player)) {
                if (event.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {
                    duellManager.duellcounter.replace(player, duellManager.duellcounter.get(player) + 1);
                    duellInventory.randomizeInventory(event.getClickedInventory());
                } else if (event.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                    duellManager.duellcounter.put(player, 0);
                    duellInventory.randomizeInventory(event.getClickedInventory());
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}
