package com.maciej916.machat.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	private static ModConfig clientConfig;
	private static ModConfig serverConfig;

	public static void bakeClient(final ModConfig config) {
		clientConfig = config;

		// Chat
		ConfigValues.customChat = ConfigHolder.CLIENT.customChat.get();
		ConfigValues.customChatFormat = ConfigHolder.CLIENT.customChatFormat.get();

		// Motd
		ConfigValues.motdEnabled = ConfigHolder.CLIENT.motdEnabled.get();
	}

	public static void bakeServer(final ModConfig config) {
		serverConfig = config;

		// Chat
		ConfigValues.customChat = ConfigHolder.SERVER.customChat.get();
		ConfigValues.customChatFormat = ConfigHolder.SERVER.customChatFormat.get();


	}

	public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}
}
