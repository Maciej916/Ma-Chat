package com.maciej916.machat;

import com.maciej916.machat.events.EventPlayerLoggedIn;
import com.maciej916.machat.events.EventServerChat;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MaChat.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerChat(ServerChatEvent event) {
        EventServerChat.event(event);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EventPlayerLoggedIn.event(event);
    }

}
