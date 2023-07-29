package com.iwuzreaper.lockablecontainers.commands;

import com.iwuzreaper.lockablecontainers.LockableContainers;
import com.iwuzreaper.lockablecontainers.util.Uni;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Settings implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("LCSettings")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }

        Player p = (Player) sender;
        if (!p.isOp() && !p.hasPermission("LockableContainers.settings") && !p.hasPermission("LockableContainers.*")) {
            sender.sendMessage(Uni.noAccess);
            return true;
        }

        if (args.length!=2) {
            p.sendMessage(Uni.incorrectArgs(2));
            return true;
        }

        String path = "";
        switch (args[0]) {
            case "isEnabled":
                path="isEnabled";
                break;
            case "preventBreak":
                path="preventBreak";
                break;
            case "claimOnPlace":
                path="claimOnPlace";
                break;
            case "preventHoppers":
                path="preventHoppers";
                break;
            default:
                p.sendMessage(Uni.noValidTarget);
                return true;
        }

        if (args[1].equalsIgnoreCase("true")) {
            p.sendMessage(Uni.commandSuccess);
            LockableContainers.config().set(path, true);
        } else if (args[1].equalsIgnoreCase("false")) {
            p.sendMessage(Uni.commandSuccess);
            LockableContainers.config().set(path, false);
        } else {
            p.sendMessage(Uni.errorTag+"Either choose 'true' or 'false'.");
        }

        LockableContainers.getInstance().saveConfig();
        LockableContainers.getInstance().reloadConfig();
        return true;
    }
}