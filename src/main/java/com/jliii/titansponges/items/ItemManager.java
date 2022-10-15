package com.jliii.titansponges.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack titanSponge;

    public static void Init(){
        createTitanSponge(1);
    }

    public static ItemStack createTitanSponge(int amount){

        ItemStack item = new ItemStack(Material.SPONGE, amount);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ItemInfo.TITAN_SPONGE_LORE);
        meta.setLore(lore);
        meta.setDisplayName(ItemInfo.SPONGE_DISPLAY_NAME);
        meta.addEnchant(Enchantment.SILK_TOUCH,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        titanSponge = item;

        return item;
    }

}
