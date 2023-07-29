package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.util.ContainerUtil;
import com.iwuzreaper.lockablecontainers.util.PDataHelper;
import com.iwuzreaper.lockablecontainers.util.Uni;
import org.bukkit.Bukkit;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ForceOwner implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("ForceOwner")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }

        Player p = (Player) sender;
        if (!p.isOp() && !p.hasPermission("LockableContainers.ForceOwner") && !p.hasPermission("LockableContainers.*")) {
            Uni.commandResponse(p,Uni.noAccess);
            return true;
        }

        boolean force = false;
        if (args.length==2) {
            if (args[1].equals("--force")) {
                force = true;
            }
        }

        if (args.length != 1 && !force) {
            Uni.commandResponse(p,Uni.incorrectArgs(1));
            return true;
        }

        Boolean validPlayerName = false;
        Player targetedPlayer = p;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(args[0])) {
                validPlayerName = true;
                targetedPlayer = player;
            }
        }

        if (!validPlayerName && !force) {
            Uni.commandResponse(p,Uni.invalidName);
        }

        if (p.getTargetBlock(5) == null) {
            Uni.commandResponse(p,Uni.noValidTarget);
            return true;
        }

        if (!(p.getTargetBlock(5).getState() instanceof TileState)) {
            Uni.commandResponse(p,Uni.noValidTarget);
            return true;
        }

        if (force) {
            TileState tileState = (TileState) p.getTargetBlock(5).getState();
            PDataHelper.Write(tileState, "container-owner", args[0], PersistentDataType.STRING);
            Uni.commandResponse(p,Uni.commandSuccess);
            return true;
        }

        TileState tileState = (TileState) p.getTargetBlock(5).getState();
        ContainerUtil.setOwner(targetedPlayer, tileState);
        Uni.commandResponse(p,Uni.commandSuccess);


        return true;
    }
}