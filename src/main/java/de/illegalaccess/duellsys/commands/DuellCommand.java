package de.illegalaccess.duellsys.commands;

import de.illegalaccess.duellsys.utils.DuellManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuellCommand implements CommandExecutor {
    private DuellManager duellManager;
    public DuellCommand(DuellManager getDuellManager) {
        duellManager = getDuellManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("request")) {
                    if (args.length >= 2) {
                        Player requested = Bukkit.getPlayer(args[1]);
                        if (requested != player)
                            duellManager.requestDuell(player, requested);
                    }
                } else if (args[0].equalsIgnoreCase("accept")) {
                    if (duellManager.requestPlayer.containsKey(player)) {
                        Player requester = duellManager.requestPlayer.get(player);
                        duellManager.joinDuell(requester, player);
                        requester.sendMessage("§7[§cDuellSystem§7] " + player.getName() + " hat deine Anfrage angenommen.");
                    }  else {
                        player.sendMessage("§7[§cDuellSystem§7] Du hast keine Anfrage bekommen.");
                    }
                }
            }
        }
        return false;
    }
}