package com.jliii.titansponges.commands;

import com.jliii.titansponges.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerCommands implements CommandExecutor {

    private Plugin plugin;

    public PlayerCommands(Plugin plugin){
        this.plugin = plugin;
    };

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!player.hasPermission("titan.sponges")) {
            player.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (args.length == 0) return false;


        if ("reload".equalsIgnoreCase(args[0])) {
            if (!player.hasPermission("titan.sponges.reload")) {
                player.sendMessage(ChatColor.RED + "No permission.");
                return true;
            }
            plugin.getLogger().info("Reloading config...");
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "Successfully reloaded config.");
            return true;
        }

        if ("add".equalsIgnoreCase(args[0])) {
            if (!player.hasPermission("titan.sponges.add")) {
                player.sendMessage(ChatColor.RED + "No permission.");
                return true;
            }
            Inventory inv = player.getInventory();
            inv.addItem(ItemManager.titanSponge);
            player.updateInventory();
            return true;
        }
        return true;
    }
}
