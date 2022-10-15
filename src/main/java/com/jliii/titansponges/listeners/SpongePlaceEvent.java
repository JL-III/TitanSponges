package com.jliii.titansponges.listeners;

import com.jliii.titansponges.Titansponges;
import com.jliii.titansponges.items.ItemManager;
import com.jliii.titansponges.util.DistributedFiller;
import com.jliii.titansponges.util.ListGenerators;
import com.jliii.titansponges.util.WorkloadRunnable;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;

import static org.bukkit.inventory.EquipmentSlot.HAND;

public class SpongePlaceEvent implements Listener {

    private final Titansponges titansponges;
    private static final Set<Location> IGNORE_LOCATIONS = new HashSet<>();
    WorkloadRunnable workloadRunnable;

    public SpongePlaceEvent(Titansponges titansponges, WorkloadRunnable workloadRunnable) {
        this.titansponges = titansponges;
        this.workloadRunnable = workloadRunnable;
    }

    Material coolDown = Material.REDSTONE;

    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent event) {

        if (event.getBlock().getType() != Material.SPONGE) {
//            if (event.getBlock().getType() == Material.BIRCH_SAPLING) {
//                new DistributedFiller(this.workloadRunnable).schemRun(event.getPlayer(), event.getBlock(), new File("/home/container/plugins/WorldEdit/schematics/house.schem"));
//            }
            return;
        }
        Player player = event.getPlayer();
        Block blockPlaced = event.getBlock();

        if (IGNORE_LOCATIONS.contains(blockPlaced.getLocation())) {
            IGNORE_LOCATIONS.remove(blockPlaced.getLocation());
            return;
        }

        //item is in main hand
        if (!event.getHand().equals(HAND)) return;
//        if (!event.getItemInHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;
        if (!event.getItemInHand().equals(ItemManager.titanSponge)) {
            player.sendMessage("This is not a titan sponge");
            return;
        }


        if (player.hasCooldown(coolDown)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Please wait " + player.getCooldown(coolDown)/20 + " seconds before using another Titan Sponge.");
            return;
        }
        boolean hasLavaOrWater = false;
        for (Block blocks : ListGenerators.getSurroundingBlocks(blockPlaced)){
            if (blocks.getType() == Material.WATER || blocks.getType() == Material.LAVA){
                hasLavaOrWater = true;
                break;
            }
        }
        if (!hasLavaOrWater) {
            player.sendMessage("You must place a Titan Sponge near water or lava.");
            event.setCancelled(true);
            return;
        }
        if (new DistributedFiller(this.workloadRunnable).cubeCheck(player, blockPlaced, 12)) {
            new DistributedFiller(this.workloadRunnable).fillSphereWithCheck(player, blockPlaced, 12, Material.SPONGE,true);
            new DistributedFiller(this.workloadRunnable).fillSphereWithCheck(player, blockPlaced, 11, Material.AIR, false);
            new DistributedFiller(this.workloadRunnable).fillSphereWithCheck(player, blockPlaced, 12, Material.AIR, false);
        } else {
            player.sendMessage(ChatColor.DARK_RED + "You are too close to a claim you do not have build permissions on, please move far away from it and try again or try using a normal sponge.");
            event.setCancelled(true);
        }
        player.setCooldown(coolDown, 20);

    }
}
