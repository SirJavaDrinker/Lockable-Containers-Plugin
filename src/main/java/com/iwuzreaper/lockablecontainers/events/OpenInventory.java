package com.iwuzreaper.lockablecontainers.events;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.StandardizedMessages;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class OpenInventory implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().getHolder()==null) {return;}

        if (e.getInventory().getHolder() instanceof DoubleChest) {
            DoubleChest doubleChest = (DoubleChest) e.getInventory().getHolder();
            boolean chestLeft = preventOpen((Container) doubleChest.getLeftSide(), e.getPlayer());
            boolean chestRight = preventOpen((Container) doubleChest.getRightSide(), e.getPlayer());
            if (chestLeft || chestRight) {
                e.getPlayer().sendMessage(StandardizedMessages.containerLocked);
                e.setCancelled(true);
            }
            return;
        }

        if (!(e.getInventory().getHolder() instanceof Container)) {
            Container container = (Container) ((Container) e.getInventory().getHolder()).getBlock().getState();
            boolean chest = preventOpen(container, e.getPlayer());
            if (chest) {
                e.getPlayer().sendMessage(StandardizedMessages.containerLocked);
                e.setCancelled(true);
            }
            return;
        }


    }

    private Boolean preventOpen(Container container, HumanEntity player) {
        Boolean preventOpen = true;
        if (!ContainerUtil.getLockState(container)) {
            preventOpen = false;
        }

        if (ContainerUtil.getOwner(container).equals(player.getUniqueId().toString())) {
            preventOpen = false;
        }

        if (ContainerUtil.isNullOwner(container)) {
            preventOpen = false;
        }

        return preventOpen;
    }
}
