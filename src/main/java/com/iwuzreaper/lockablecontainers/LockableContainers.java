package com.iwuzreaper.lockablecontainers;

import com.iwuzreaper.lockablecontainers.commands.ClaimContainer;
import com.iwuzreaper.lockablecontainers.commands.ForceOwner;
import com.iwuzreaper.lockablecontainers.commands.ToggleLock;
import com.iwuzreaper.lockablecontainers.commands.UnClaimContainer;
import com.iwuzreaper.lockablecontainers.events.BreakBlock;
import com.iwuzreaper.lockablecontainers.events.InventoryMoveItem;
import com.iwuzreaper.lockablecontainers.events.OpenInventory;
import com.iwuzreaper.lockablecontainers.events.PlaceBlock;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockableContainers extends JavaPlugin {

    private static LockableContainers instance;
    public static LockableContainers getInstance() {return instance;}
    public static FileConfiguration config() {return getInstance().getConfig();}

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        this.getCommand("ToggleLock").setExecutor(new ToggleLock());
        this.getCommand("ForceOwner").setExecutor(new ForceOwner());
        this.getCommand("UnClaimContainer").setExecutor(new UnClaimContainer());
        this.getCommand("ClaimContainer").setExecutor(new ClaimContainer());




        this.getServer().getPluginManager().registerEvents(new PlaceBlock(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryMoveItem(), this);
        this.getServer().getPluginManager().registerEvents(new OpenInventory(), this);
        this.getServer().getPluginManager().registerEvents(new BreakBlock(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
