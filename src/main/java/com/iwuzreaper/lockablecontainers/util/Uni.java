package com.iwuzreaper.lockablecontainers.util;

import com.iwuzreaper.lockablecontainers.LockableContainers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Uni {
    //There's 1000% a better way to set this up... but I'm lazy and this works.
    
    public static void commandResponse(Player player, String message) { player.sendMessage(message);}

    public static void actionResponse(Player player, String message) {
        player.sendActionBar(message);
    }



    public static String tag = ChatColor.WHITE+"["+ChatColor.YELLOW+"LC"+ChatColor.WHITE+"]"+ChatColor.RESET+" ";
    public static String errorTag = ChatColor.WHITE+"["+ChatColor.RED+"LC"+ChatColor.WHITE+"]"+ChatColor.RESET+" ";

    public static String noAccess = errorTag +"Sorry, it seems you don't have the permissions for this action."+ChatColor.RESET;

    public static String incorrectArgs(int args) {
        return errorTag +"Sorry, but this requires "+args+" args."+ChatColor.RESET;
    }

    public static String invalidName = errorTag +"Please input a valid name."+ChatColor.RESET;

    public static String notOwnerContainer = errorTag +"This does not belong to you."+ChatColor.RESET;

    public static String containerLocked = errorTag +"This container appears to be locked."+ChatColor.RESET;

    public static String containerAlreadyClaimed = errorTag +"This container is already claimed."+ChatColor.RESET;

    public static String containerAlreadyUnclaimed = errorTag +"This container is already unclaimed."+ChatColor.RESET;

    public static String noValidTarget = errorTag +"Invalid target."+ChatColor.RESET;

    public static String commandSuccess = errorTag +"Action successfully completed."+ChatColor.RESET;

}

