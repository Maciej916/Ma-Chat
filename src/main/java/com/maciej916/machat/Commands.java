package com.maciej916.machat;

import com.maciej916.machat.commands.*;
import com.maciej916.machat.config.ConfigValues;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class Commands {

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();

        boolean dedicated = event.getServer().isDedicatedServer();
        if (dedicated || (!dedicated && ConfigValues.client_enable)) {

            // Rules
            if (ConfigValues.rules_enabled) {
                CommandRules.register(dispatcher);
            }

            // Mac
            CommandMac.register(dispatcher);

            // Chat
            CommandChat.register(dispatcher);
        }
    }

}
