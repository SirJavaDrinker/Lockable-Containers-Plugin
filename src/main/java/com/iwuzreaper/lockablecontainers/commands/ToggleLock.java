package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.Uni;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
            sender.sendMessage(Uni.noAccess);
            return true;
        }

        if (p.getTargetBlock(5)==null) {
            Uni.commandResponse(p, Uni.noValidTarget);
            return true;
        }

        if (!(p.getTargetBlock(5).getState() instanceof TileState)) {
            Uni.commandResponse(p, Uni.noValidTarget);
            return true;
        }

        TileState tileState = (TileState) p.getTargetBlock(5).getState();
        if (!ContainerUtil.checkOwner(p, tileState)){
            Uni.commandResponse(p, Uni.notOwnerContainer);
            return true;
        }

        if (ContainerUtil.toggleLockState(tileState)) {
            Uni.commandResponse(p, Uni.tag + "Container is now locked");
        } else {
            Uni.commandResponse(p, Uni.tag + "Container is now unlocked");
        }


        return true;
    }
}
