package com.maciej916.machat.events;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Log;
import com.maciej916.machat.libs.Text;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.BufferedReader;
import java.io.FileReader;

public final class EventPlayerLoggedIn {

    public static void event(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        if (!player.server.isDedicatedServer() && !ConfigValues.clientEnable) {
            return;
        }

        if (ConfigValues.motdEnabled) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(ConfigValues.mainCatalog + "motd.txt"));
                String line = reader.readLine();
                while (line != null) {
                    TextComponent msg = new StringTextComponent(Text.replaceVariables(player, line));
                    player.sendMessage(msg);
                    line = reader.readLine();
                }
                reader.close();
            } catch (Exception e) {
                Log.err("Failed to load motd.txt");
                Log.err(e.getMessage());
            }
        }
    }
}
