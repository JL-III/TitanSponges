package com.jliii.titansponges.items;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemInfo {

    public static final String SPONGE_DISPLAY_NAME = "§x§7§F§0§0§0§0§k %§x§8§8§0§0§0§1§l T§x§9§2§0§0§0§2§lh§x§9§B§0§0§0§3§le§x§A§5§0§0§0§4§l T§x§A§E§0§0§0§4§li§x§B§8§0§0§0§4§lt§x§C§2§0§0§0§4§la§x§C§C§0§0§0§3§ln§x§D§6§0§0§0§3§l S§x§E§0§0§0§0§2§lp§x§E§A§0§0§0§2§lo§x§F§5§0§0§0§1§ln§x§F§5§0§0§0§1§lg§x§F§5§0§0§0§1§le§x§F§5§0§0§0§1§l§k %";

    public static final String TITAN_SPONGE_LORE = "§8Ancient Power §x§F§F§0§0§4§C♆";

    public static boolean isTitanSponge(ItemStack item){

        if (item.getItemMeta() == null) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (TITAN_SPONGE_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

}
