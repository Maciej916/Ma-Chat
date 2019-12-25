package com.maciej916.machat.events;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Log;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static com.maciej916.machat.libs.text.TextFormat.*;

public final class EventPlayerLoggedIn {

    public static void event(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

        boolean dedicated = player.server.isDedicatedServer();
        if (dedicated || (!dedicated && ConfigValues.client_enable)) {
            if (ConfigValues.motd_enabled) {
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(ConfigValues.mainCatalog + "motd.txt"));
                    String line = reader.readLine();
                    while (line != null) {
                        ArrayList<Object> var = variableFinder(line, true);
                        var = replacePlayer(var, player);
                        player.sendMessage(componentBuilder(var));
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

}
