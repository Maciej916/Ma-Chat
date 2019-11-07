package com.maciej916.machat.config;

import com.maciej916.machat.MaChat;
import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {

	private final static int MAX = Integer.MAX_VALUE;

	// Chat
	final ForgeConfigSpec.BooleanValue customChat;

	// Motd
	final ForgeConfigSpec.BooleanValue motdEnabled;

	// Rules
	final ForgeConfigSpec.BooleanValue rulesEnabled;
	final ForgeConfigSpec.IntValue rulesPerPage;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		// Chat
		builder.push("custom_chat");
			customChat = builder.translation(MaChat.MODID + ".config.customChat").define("enabled", true);
		builder.pop();

		// Motd
		builder.push("motd");
			motdEnabled = builder.translation(MaChat.MODID + ".config.motdEnabled").define("enabled", true);
		builder.pop();

		// Rules
		builder.push("rules");
			rulesEnabled = builder.translation(MaChat.MODID + ".config.rulesEnabled").define("enabled", true);
			rulesPerPage = builder.translation(MaChat.MODID + ".config.rulesPerPage").defineInRange("per_page", 5, 1, MAX);
		builder.pop();

	}
}