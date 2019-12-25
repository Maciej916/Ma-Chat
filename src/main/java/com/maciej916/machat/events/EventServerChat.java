package com.maciej916.machat.events;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.data.DataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;

import java.util.ArrayList;

import static com.maciej916.machat.libs.text.TextFormat.*;
import static com.maciej916.machat.libs.text.TextFormat.componentBuilder;

public final class EventServerChat {

    public static ArrayList<Object> replaceChat(ArrayList<Object> var, String message) {
        ArrayList<String> replace = new ArrayList<>();
        ArrayList<Object> replaceWith = new ArrayList<>();
        replace.add("message");
        replaceWith.add(new StringTextComponent(message));
        return variableReplacer(var, replace, replaceWith);
    }

    public static void event(ServerChatEvent event) {
        ServerPlayerEntity player = event.getPlayer();

        boolean dedicated = player.server.isDedicatedServer();
        if (dedicated || (!dedicated && ConfigValues.client_enable)) {
            if (ConfigValues.custom_chat) {
                String message = event.getMessage();

                boolean colors = false;
                if (player.hasPermissionLevel(4)) {
                    colors = true;
                }

                ArrayList<Object> var = variableFinder(DataManager.getChat().getFormat(), colors);
                var = replacePlayer(var, player);
                var = replaceChat(var, message);

                event.setComponent(componentBuilder(var));
            }
        }
    }

}