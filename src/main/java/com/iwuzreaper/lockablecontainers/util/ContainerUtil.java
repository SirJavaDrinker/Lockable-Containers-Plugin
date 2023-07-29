package com.iwuzreaper.lockablecontainers.util;

import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class ContainerUtil {


    public static void writeToContainer(TileState tileState, String key, Object value, PersistentDataType type) {
        if (tileState instanceof Chest) {
            if (((Chest) tileState).getInventory().getHolder() instanceof DoubleChest) {
                DoubleChest doubleChest = (DoubleChest) ((Chest) tileState).getInventory().getHolder();
                PDataHelper.Write(((Container) doubleChest.getLeftSide()), key, value, type);
                PDataHelper.Write(((Container) doubleChest.getRightSide()), key, value, type);
            } else {
                PDataHelper.Write(tileState, key, value, type);
            }
        } else {
            PDataHelper.Write(tileState, key, value, type);
        }
    }
    public static Object readFromContainer(TileState tileState, String key, Object fallback, PersistentDataType type) {
        if (tileState instanceof Chest) {
            if (((Chest) tileState).getInventory().getHolder() instanceof DoubleChest) {
                DoubleChest doubleChest = (DoubleChest) ((Chest) tileState).getInventory().getHolder();
                PDataHelper.Read(((Container) doubleChest.getLeftSide()), key, fallback, type);
                return PDataHelper.Read(((Container) doubleChest.getRightSide()), key, fallback, type);
            } else {
                return PDataHelper.Read(tileState, key, fallback, type);
            }
        } else {
            return PDataHelper.Read(tileState, key, fallback, type);
        }
    }

    public static void setOwner(Player player, TileState tileState) {
        writeToContainer(tileState, "container-owner", player.getUniqueId().toString(), PersistentDataType.STRING);
    }
    public static String getOwner(TileState tileState) {
        return (String) readFromContainer(tileState, "container-owner", "null", PersistentDataType.STRING);
    }
    public static boolean checkOwner(Player player, TileState tileState) {
        String containerOwner = (String) readFromContainer(tileState, "container-owner", "null", PersistentDataType.STRING);
        if (containerOwner.equalsIgnoreCase("null")) {
            return false;
        } else if (!containerOwner.equalsIgnoreCase(player.getUniqueId().toString())) {
            return false;
        } else {
            return true;
        }
    }

    public static void setOwnerNull(TileState tileState) {
        writeToContainer(tileState, "container-owner", "null", PersistentDataType.STRING);
    }
    public static boolean isOwnerNull(TileState tileState) {
        String containerOwner = (String) readFromContainer(tileState, "container-owner", "null", PersistentDataType.STRING);
        if (containerOwner.equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }


    public static void setLockState(TileState tileState, boolean bool) {
        writeToContainer(tileState, "container-lock-state", bool, PersistentDataType.BOOLEAN);
    }
    public static boolean toggleLockState(TileState tileState) {
        Boolean lockState = getLockState(tileState);
        if (lockState) {
            setLockState(tileState, false);
            return false;
        } else {
            setLockState(tileState, true);
            return true;
        }
    }
    public static boolean getLockState(TileState tileState) {
        return (Boolean) readFromContainer(tileState, "container-lock-state", false, PersistentDataType.BOOLEAN);
    }


}
