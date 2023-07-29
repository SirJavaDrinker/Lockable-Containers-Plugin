package com.iwuzreaper.lockablecontainers.events;

import com.iwuzreaper.lockablecontainers.LockableContainers;
import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent; //This sucks


public class InventoryMoveItem implements Listener {

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        if (!LockableContainers.config().getBoolean("preventHoppers")) {return;}
        if (e.getSource().getHolder()==null) {return;}
        if (e.getDestination().getHolder()==null) {return;}

        if (e.getDestination().getHolder() instanceof DoubleChest) {
            DoubleChest destinationDC = (DoubleChest) e.getDestination().getHolder();

            if (shouldCancel((Container) e.getSource().getHolder(), (Container) destinationDC.getLeftSide())) {
                e.setCancelled(true);
                return;
            }
            if (shouldCancel((Container) e.getSource().getHolder(), (Container) destinationDC.getRightSide())) {
                e.setCancelled(true);
                return;
            }

        }
        if (e.getSource().getHolder() instanceof DoubleChest) {
            DoubleChest sourceDC = (DoubleChest) e.getSource().getHolder();

            if (shouldCancel((Container) sourceDC.getLeftSide(), (Container) e.getDestination().getHolder())) {
                e.setCancelled(true);
                return;
            }
            if (shouldCancel((Container) sourceDC.getRightSide(), (Container) e.getDestination().getHolder())) {
                e.setCancelled(true);
                return;
            }
        }

        if (!(e.getSource().getHolder() instanceof Container)) {return;}
        if (!(e.getDestination().getHolder() instanceof Container)) {return;}

        Container sourceContainer = (Container) ((Container) e.getSource().getHolder()).getBlock().getState();
        Container destinationContainer = (Container) ((Container) e.getDestination().getHolder()).getBlock().getState();

        if (shouldCancel(sourceContainer, destinationContainer)) {
            e.setCancelled(true);
        }


    }

    private boolean shouldCancel(Container source, Container destination) {

        Boolean cancelTransfer = true;
        if (!ContainerUtil.getLockState(source)) {
            cancelTransfer = false;
        }

        if (ContainerUtil.getOwner(destination).equals(ContainerUtil.getOwner(source))) {
            cancelTransfer = false;
        }


        if (ContainerUtil.isOwnerNull(source)) {
            cancelTransfer = false;
        }
        return cancelTransfer;
    }
}
