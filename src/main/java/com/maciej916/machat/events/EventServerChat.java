package com.maciej916.machat.events;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.data.DataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.ServerChatEvent;

import static com.maciej916.machat.libs.Text.replaceVariables;

public final class EventServerChat {

    public static String formatChat(ServerPlayerEntity player, String message) {
        String text = DataManager.getChat().format;
        text = text.replaceAll("%message%", message);
        return replaceVariables(player, text);
    }

    public static void event(ServerChatEvent event) {
        if (ConfigValues.customChat) {
            ServerPlayerEntity player = event.getPlayer();
            String message = event.getMessage();

            if (player.hasPermissionLevel(4)) {
                message = message.replaceAll("&", Character.toString ((char) 167));
            }

            TextComponent msg = new StringTextComponent(formatChat(player, message));
            event.setComponent(msg);
        }
    }
}