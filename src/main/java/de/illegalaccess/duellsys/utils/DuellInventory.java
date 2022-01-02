package de.illegalaccess.duellsys.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class DuellInventory {

    public Inventory createInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 5 * 9, "§7» §bDuell");

        for (int i=0; i<5*9; i++){
            inventory.setItem(i,new itemBuilder(Material.BLACK_STAINED_GLASS_PANE).displayname(" ").build());
        }

        inventory.setItem(22, new itemBuilder(Material.RED_STAINED_GLASS_PANE).displayname("§2Click to start").build());

        return inventory;
    }

    public void randomizeInventory(Inventory inventory) {
        int targetSlot = new Random().nextInt(44);

        for (int i=0; i<5*9; i++){
            inventory.setItem(i,new itemBuilder(Material.BLACK_STAINED_GLASS_PANE).displayname(" ").build());
        }

        inventory.setItem(targetSlot, new itemBuilder(Material.LIME_STAINED_GLASS_PANE).displayname("§2Click").build());
    }


} 