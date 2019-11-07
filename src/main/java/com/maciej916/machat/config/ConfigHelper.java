package com.maciej916.machat.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	private static ModConfig clientConfig;
	private static ModConfig serverConfig;

	public static void bakeClient(final ModConfig config) {
		clientConfig = config;

		// Enable
		ConfigValues.clientEnable = ConfigHolder.CLIENT.clientEnable.get();
	}

	public static void bakeServer(final ModConfig config) {
		serverConfig = config;

		// Chat
		ConfigValues.customChat = ConfigHolder.SERVER.customChat.get();

		// Motd
		ConfigValues.motdEnabled = ConfigHolder.SERVER.motdEnabled.get();

		// Rules
		ConfigValues.rulesEnabled = ConfigHolder.SERVER.rulesEnabled.get();
		ConfigValues.rulesPerPage = ConfigHolder.SERVER.rulesPerPage.get();
	}

	public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}
}
