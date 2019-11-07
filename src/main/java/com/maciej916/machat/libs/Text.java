package com.maciej916.machat.libs;

import com.maciej916.machat.classes.chat.ChatData;
import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.data.DataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import javax.xml.crypto.Data;

public class Text {

    private static String rankColor(ServerPlayerEntity player) {
        String username = player.getDisplayName().getString();
        if (player.hasPermissionLevel(4)) {
            return "&4" + username + "&r";
        }
        if (player.hasPermissionLevel(3)) {
            return "&c" + username + "&r";
        }
        if (player.hasPermissionLevel(2)) {
            return "&6" + username + "&r";
        }
        if (player.hasPermissionLevel(1)) {
            return "&2" + username + "&r";
        }
        return "&8" + username + "&r";
    }

    public static String replaceVariables(ServerPlayerEntity player, String text) {
        text = text.replaceAll("%username%", rankColor(player));
        text = text.replaceAll("%dimension%", player.dimension.getKey(player.dimension).toString());
        text = text.replaceAll("%players%", String.valueOf(player.server.getPlayerList().getOnlinePlayerNames().length));
        text = text.replaceAll("%max_players%", String.valueOf(player.server.getPlayerList().getMaxPlayers()));
        text = text.replaceAll("&", Character.toString ((char) 167));
        return text;
    }
}