package de.illegalaccess.duellsys.utils;

import de.illegalaccess.duellsys.DuellSys;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DuellManager {
    private DuellInventory duellInventory;
    public DuellManager(DuellInventory getDuellInventory) {
        duellInventory = getDuellInventory;
    }

    public HashMap< Player, Player> duellPlayer = new HashMap<>();

    public Map< Player, Player> requestPlayer = new HashMap<>(); //p1: requested, p2: requester

    public HashMap<Player, Integer> duellcounter = new HashMap<>();

    public void requestDuell(Player requester, Player requested) {
        if (!requestPlayer.containsKey(requester)) {
            TextComponent request = new TextComponent();
            request.setText("§7[§cDuellSystem§7] §e" + requester.getName() + " möchte gegen dich antreten im Duell. \n");

            TextComponent accept = new TextComponent("§7[§2Annehmen§7]");
            accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duell accept"));

            request.addExtra(accept);

            requestPlayer.put(requested, requester);

            requested.spigot().sendMessage(request);
        }
    }

    public void joinDuell(Player requester, Player requested) {
        int time = 30;
        requestPlayer.remove(requested);
        duellPlayer.put(requester, requested);

        //open Inventory
        requested.openInventory(duellInventory.createInventory(requested));
        requester.openInventory(duellInventory.createInventory(requester));
        //timer
        Bukkit.getScheduler().scheduleSyncDelayedTask(DuellSys.plugin, new Runnable() {
            @Override
            public void run() {
                requester.closeInventory();
                requested.closeInventory();
                if (duellcounter.containsKey(requester) && duellcounter.containsKey(requested)){
                    int countRequester = duellcounter.get(requester);
                    int countRequested = duellcounter.get(requested);
                    if (countRequester > countRequested) {
                        requester.sendMessage("§7[§cDuellSystem§7] §eDu hast gewonnen. " + "§2" +countRequester + "§7:§c" + countRequested);
                        requested.sendMessage("§7[§cDuellSystem§7] §eDu hast verloren. " + "§c" +countRequested + "§7:§2" + countRequester);
                    } else if (countRequested < countRequester) {
                        requested.sendMessage("§7[§cDuellSystem§7] §eDu hast gewonnen. "+ "§2" +countRequested + "§7:§c" + countRequester);
                        requester.sendMessage("§7[§cDuellSystem§7] §eDu hast verloren."+ "§c" +countRequester + "§7:§2" + countRequested);
                    } else if (countRequested == countRequester) {
                        requested.sendMessage("§7[§cDuellSystem§7] §eGleichstand keiner hat gewonnen."+ "§2" +countRequested + "§7:§c" + countRequester);
                        requester.sendMessage("§7[§cDuellSystem§7] §eGleichstand keiner hat gewonnen."+ "§2" +countRequester + "§7:§c" + countRequester);
                    }
                } else {
                    requested.sendMessage("§7[§cDuellSystem§7] §eDu oder dein Mitspieler waren afk.");
                    requester.sendMessage("§7[§cDuellSystem§7] §eDu oder dein Mitspieler waren afk.");
                }
                duellcounter.remove(requested);
                duellcounter.remove(requester);
                duellPlayer.remove(requested);
            }
        }, time*20);
    }
}
