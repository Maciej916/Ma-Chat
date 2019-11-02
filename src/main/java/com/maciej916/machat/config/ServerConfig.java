package com.maciej916.machat.config;

import com.maciej916.machat.MaChat;
import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {

	// Chat
	final ForgeConfigSpec.BooleanValue customChat;
	final ForgeConfigSpec.ConfigValue<String> customChatFormat;

	// Motd
	final ForgeConfigSpec.BooleanValue motdEnabled;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		// Chat
		builder.push("custom_chat");
			customChat = builder.translation(MaChat.MODID + ".config.customChat").define("enabled", true);
			customChatFormat = builder.translation(MaChat.MODID + ".config.customChatFormat").define("format", "%username%: %message%");
		builder.pop();

		// Motd
		builder.push("motd");
			motdEnabled = builder.translation(MaChat.MODID + ".config.motdEnabled").define("enabled", true);
		builder.pop();
	}
}