package com.maciej916.machat.config;

import com.maciej916.machat.MaChat;
import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {

	final ForgeConfigSpec.BooleanValue customChat;
	final ForgeConfigSpec.ConfigValue<String> customChatFormat;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		// Chat
		builder.push("custom_chat");
			customChat = builder.translation(MaChat.MODID + ".config.customChat").define("enabled", true);
			customChatFormat = builder.translation(MaChat.MODID + ".config.customChatFormat").define("format", "%username%: %message%");
		builder.pop();

	}
}