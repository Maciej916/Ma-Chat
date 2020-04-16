package com.maciej916.machat.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	private static ModConfig clientConfig;
	private static ModConfig serverConfig;

	public static void bakeClient(final ModConfig config) {
		clientConfig = config;

		// Enable
		ConfigValues.client_enable = ConfigHolder.CLIENT.clientEnable.get();
	}

	public static void bakeServer(final ModConfig config) {
		serverConfig = config;

		// Chat
		ConfigValues.custom_chat = ConfigHolder.SERVER.custom_chat.get();
		ConfigValues.rank_colors = ConfigHolder.SERVER.rank_colors.get();
		ConfigValues.rank_color_0 = ConfigHolder.SERVER.rank_color_0.get();
		ConfigValues.rank_color_1 = ConfigHolder.SERVER.rank_color_1.get();
		ConfigValues.rank_color_2 = ConfigHolder.SERVER.rank_color_2.get();
		ConfigValues.rank_color_3 = ConfigHolder.SERVER.rank_color_3.get();
		ConfigValues.rank_color_4 = ConfigHolder.SERVER.rank_color_4.get();

		// Motd
		ConfigValues.motd_enabled = ConfigHolder.SERVER.motdEnabled.get();

		// Rules
		ConfigValues.rules_enabled = ConfigHolder.SERVER.rulesEnabled.get();
		ConfigValues.rules_per_page = ConfigHolder.SERVER.rulesPerPage.get();

		// Chat
		ConfigValues.chatClearTemplate = ConfigHolder.SERVER.chatClearTemplate.get();
	}

	public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}
}
