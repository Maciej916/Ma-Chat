package com.maciej916.machat.events;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Text;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.ServerChatEvent;

public final class EventServerChat {

    public static void event(ServerChatEvent event) {
        if (ConfigValues.customChat) {

            ServerPlayerEntity player = event.getPlayer();
            String message = event.getMessage();

            if (player.hasPermissionLevel(4)) {
                message = message.replaceAll("&", Character.toString ((char) 167));
            }

            TextComponent msg = Text.replaceChatVariables(player, message);
            event.setComponent(msg);
        }
    }
}