package com.jliii.titansponges;

import com.jliii.titansponges.commands.PlayerCommands;
import com.jliii.titansponges.commands.PlayerCommandsTabComplete;
import com.jliii.titansponges.items.ItemManager;
import com.jliii.titansponges.listeners.SpongePlaceEvent;
import com.jliii.titansponges.util.WorkloadRunnable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Titansponges extends JavaPlugin {

    private final Logger LOGGER;
    private final FileConfiguration CONFIG;
    private final Plugin PLUGIN;
    private final WorkloadRunnable workloadRunnable = new WorkloadRunnable();

    public Titansponges() {
        PLUGIN = this;
        LOGGER = getLogger();
        CONFIG = getConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemManager.Init();
        Bukkit.getScheduler().runTaskTimer(this, this.workloadRunnable, 1, 1);
        Bukkit.getPluginManager().registerEvents(new SpongePlaceEvent(this, workloadRunnable),this);
        Objects.requireNonNull(getCommand("titansponges")).setExecutor(new PlayerCommands(this));
        Objects.requireNonNull(getCommand("titansponges")).setTabCompleter(new PlayerCommandsTabComplete());

//        if (Bukkit.getRecipe(new NamespacedKey(this, "titanSponge")) == null) {
//            ShapelessRecipe titanSpongeRecipe = new ShapelessRecipe(new NamespacedKey(this, "titanSponge"), ItemManager.titanSponge);
//            titanSpongeRecipe.addIngredient(1, Material.SPONGE);
//            titanSpongeRecipe.addIngredient(1, ItemManager.powerCrystal);
//            Bukkit.addRecipe(titanSpongeRecipe);
//        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
