package com.iwuzreaper.lockablecontainers.util;

import org.bukkit.ChatColor;

public class StandardizedMessages {
    public static String pluginTag = ChatColor.WHITE+"["+ChatColor.YELLOW+"LC"+ChatColor.WHITE+"]"+ChatColor.RESET+" ";
    public static String errorPluginTag = ChatColor.WHITE+"["+ChatColor.RED+"LC"+ChatColor.WHITE+"]"+ChatColor.RESET+" ";


    public static String noAccess = errorPluginTag+"Sorry, it seems you don't have the permissions for this action."+ChatColor.RESET;

    public static String incorrectArgs(int args) {
        return errorPluginTag+"Sorry, but this requires "+args+" args."+ChatColor.RESET;
    }

    public static String invalidName = errorPluginTag+"Please input a valid name."+ChatColor.RESET;

    public static String notOwnerContainer = errorPluginTag+"This does not belong to you."+ChatColor.RESET;

    public static String containerLocked = errorPluginTag+"This container appears to be locked."+ChatColor.RESET;

    public static String containerAlreadyClaimed = errorPluginTag+"This container is already claimed."+ChatColor.RESET;

    public static String containerAlreadyUnclaimed = errorPluginTag+"This container is already unclaimed."+ChatColor.RESET;

    public static String noValidTarget = errorPluginTag+"No valid target present..."+ChatColor.RESET;

    public static String commandSuccess = errorPluginTag+"Command successfully executed."+ChatColor.RESET;

}

