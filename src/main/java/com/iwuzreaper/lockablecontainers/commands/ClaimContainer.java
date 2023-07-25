package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.StandardizedMessages;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClaimContainer implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("ClaimContainer")) {
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }
        Player p = (Player) sender;
        if (!p.isOp() && !p.hasPermission("LockableContainers.ClaimContainer") && !p.hasPermission("LockableContainers.*")) {
            sender.sendMessage(StandardizedMessages.noAccess);
            return true;
        }

        if (p.getTargetBlock(5)==null) {
            sender.sendMessage(StandardizedMessages.noValidTarget);
            return true;
        }

        if (!(p.getTargetBlock(5).getState() instanceof TileState)) {
            sender.sendMessage(StandardizedMessages.noValidTarget);
            return true;
        }

        TileState tileState = (TileState) p.getTargetBlock(5).getState();
        if (!ContainerUtil.isNullOwner(tileState)){
            p.sendMessage(StandardizedMessages.containerAlreadyClaimed);
            return true;
        }

        ContainerUtil.setOwner(p, tileState);
        p.sendMessage(StandardizedMessages.commandSuccess);

        return true;
    }
}
