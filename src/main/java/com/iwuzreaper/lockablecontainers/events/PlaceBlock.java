package com.iwuzreaper.lockablecontainers.events;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class PlaceBlock implements Listener {

    @EventHandler
    public void PlaceBlock(BlockPlaceEvent e) {
        if (!(e.getBlock().getState() instanceof Container)) {return;}
        Container container = (Container) e.getBlock().getState();

        ContainerUtil.setOwner(e.getPlayer(), container);

    }
}
