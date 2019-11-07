package com.maciej916.machat.config;

import com.maciej916.machat.MaChat;
import net.minecraftforge.common.ForgeConfigSpec;

final class ClientConfig {

	// Client
	final ForgeConfigSpec.BooleanValue clientEnable;

	ClientConfig(final ForgeConfigSpec.Builder builder) {
		// Client
		clientEnable = builder.translation(MaChat.MODID + ".config.enable").define("enabled", false);


	}
}