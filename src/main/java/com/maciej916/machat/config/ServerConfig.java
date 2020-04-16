package com.maciej916.machat.config;

import com.maciej916.machat.MaChat;
import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {

	private final static int MAX = Integer.MAX_VALUE;

	// Chat
	final ForgeConfigSpec.BooleanValue custom_chat;
	final ForgeConfigSpec.BooleanValue rank_colors;

	final ForgeConfigSpec.ConfigValue<String> rank_color_4;
	final ForgeConfigSpec.ConfigValue<String> rank_color_3;
	final ForgeConfigSpec.ConfigValue<String> rank_color_2;
	final ForgeConfigSpec.ConfigValue<String> rank_color_1;
	final ForgeConfigSpec.ConfigValue<String> rank_color_0;

	// Motd
	final ForgeConfigSpec.BooleanValue motdEnabled;

	// Rules
	final ForgeConfigSpec.BooleanValue rulesEnabled;
	final ForgeConfigSpec.IntValue rulesPerPage;

	// Chat
	final ForgeConfigSpec.ConfigValue<String> chatClearTemplate;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		// Chat
		builder.push("custom_chat");
			custom_chat = builder.define("enabled", true);
			rank_colors = builder.define("rank_colors", true);
		builder.push("colors");
			rank_color_4 = builder.define("4", "&4");
			rank_color_3 = builder.define("3", "&c");
			rank_color_2 = builder.define("2", "&6");
			rank_color_1 = builder.define("1", "&2");
			rank_color_0 = builder.define("0", "&8");
		builder.pop();
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

		// Chat
		builder.push("chat");
		chatClearTemplate = builder.define("0", "\n&8Chat cleared\n");
		builder.pop();

	}
}