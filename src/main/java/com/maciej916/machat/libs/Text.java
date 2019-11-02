package com.maciej916.machat.libs;

import com.maciej916.machat.config.ConfigValues;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;

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

    public static StringTextComponent replaceVariables(ServerPlayerEntity player, String text) {
        String format = ConfigValues.customChatFormat;

        format = format.replaceAll("%username%", rankColor(player));
        format = format.replaceAll("%message%", text);

        format = format.replaceAll("%dimension%", player.dimension.getKey(player.dimension).toString());
        format = format.replaceAll("%players%", String.valueOf(player.server.getPlayerList().getOnlinePlayerNames().length));
        format = format.replaceAll("%max_players%", String.valueOf(player.server.getPlayerList().getMaxPlayers()));

        format = format.replaceAll("&", Character.toString ((char) 167));
        return new StringTextComponent(format);
    }
}
