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
        if (!event.getServer().isDedicatedServer() && !ConfigValues.clientEnable) {
            return;
        }

        // Rules
        if (ConfigValues.rulesEnabled) {
            CommandRules.register(dispatcher);
        }

        // Reload
        CommandMacReload.register(dispatcher);




    }
}
