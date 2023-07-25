package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.PDataHelper;
import com.iwuzreaper.lockablecontainers.util.StandardizedMessages;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ToggleLock implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("ToggleLock")) {
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }
        Player p = (Player) sender;
        if (!p.isOp() && !p.hasPermission("LockableContainers.ToggleLock") && !p.hasPermission("LockableContainers.*")) {
            sender.sendMessage(StandardizedMessages.noAccess);
            return true;
        }

        if (p.getTargetBlock(5)==null) {
            sender.sendMessage(StandardizedMessages.errorPluginTag+"No valid target present.");
            return true;
        }

        if (!(p.getTargetBlock(5).getState() instanceof TileState)) {
            sender.sendMessage(StandardizedMessages.errorPluginTag+"No valid target present.");
            return true;
        }

        TileState tileState = (TileState) p.getTargetBlock(5).getState();
        if (!ContainerUtil.isOwner(p, tileState)){
            p.sendMessage(StandardizedMessages.errorPluginTag+"You do not own this container.");
            return true;
        }

        if (ContainerUtil.toggleLockState(tileState)) {
            sender.sendMessage(StandardizedMessages.pluginTag+"Container is now locked.");
        } else {
            sender.sendMessage(StandardizedMessages.pluginTag+"Container is now unlocked.");
        }


        return true;
    }
}
