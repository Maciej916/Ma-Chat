package com.maciej916.machat;

import com.maciej916.machat.config.ConfigHolder;
import com.maciej916.machat.data.DataLoader;
import com.maciej916.machat.libs.auto_message.AutoMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaChat.MODID)
public class MaChat
{
    public static final String MODID = "ma-chat";
    public static AutoMessage autoMessage;
    public static boolean chatEnabled = true;

    public MaChat() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerSetup);
        MinecraftForge.EVENT_BUS.register(Commands.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerSetup(FMLCommonSetupEvent event) {
        DataLoader.setupMain();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        DataLoader.load();
        autoMessage = new AutoMessage(event.getServer());
        autoMessage.startTimer();
    }

    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        autoMessage.stopTimer();
    }

}
