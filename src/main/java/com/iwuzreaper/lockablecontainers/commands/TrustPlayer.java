package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.StandardizedMessages;
import org.bukkit.Bukkit;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrustPlayer implements CommandExecutor {
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
        if (!p.isOp() && !p.hasPermission("LockableContainers.TrustPlayer") && !p.hasPermission("LockableContainers.*")) {
            sender.sendMessage(StandardizedMessages.noAccess);
            return true;
        }

        if (args.length!=1) {
            sender.sendMessage(StandardizedMessages.incorrectArgs(1));
            return true;
        }

        if (p.getName().equalsIgnoreCase(args[0])) {
            sender.sendMessage(StandardizedMessages.errorPluginTag+"I-... think that's you...");
        }

        Boolean validPlayerName = false;
        Player targetedPlayer;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(args[0])) {
                validPlayerName=true;
                targetedPlayer=player;
            }
        }

        if (!validPlayerName) {
            sender.sendMessage(StandardizedMessages.invalidName);
        }

        if (p.getTargetBlock(5) == null) {
            sender.sendMessage(StandardizedMessages.errorPluginTag + "No valid target present.");
            return true;
        }

        if (!(p.getTargetBlock(5).getState() instanceof TileState)) {
            sender.sendMessage(StandardizedMessages.errorPluginTag + "No valid target present.");
            return true;
        }

        TileState tileState = (TileState) p.getTargetBlock(5).getState();
        if (!ContainerUtil.isOwner(p, tileState)){
            return true;
        }


        return true;
    }
}
