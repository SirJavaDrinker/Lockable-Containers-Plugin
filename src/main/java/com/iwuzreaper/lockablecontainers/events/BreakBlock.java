package com.iwuzreaper.lockablecontainers.events;

import com.iwuzreaper.lockablecontainers.LockableContainers;
import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.StandardizedMessages;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!LockableContainers.config().getBoolean("preventBreak")) {return;}
        if (!(e.getBlock().getState() instanceof Container)) {return;}
        Container container = (Container) e.getBlock().getState();
        Player p = e.getPlayer();


        if (ContainerUtil.isNullOwner(container)) {
            return;
        }

        if (!ContainerUtil.getOwner(container).equals(p.getUniqueId().toString()) && ContainerUtil.getLockState(container))  {
            p.sendMessage(StandardizedMessages.notOwnerContainer);
            e.setCancelled(true);
            return;
        }

    }
}
