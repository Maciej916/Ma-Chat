package com.maciej916.machat.libs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.maciej916.machat.MaChat.MODID;
import static com.maciej916.machat.libs.Methods.isDev;

public class Log {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void log(String text) {
        LOGGER.info(MODID + " " + text);
    }

    public static void err(String text) {
        LOGGER.error(MODID + " " + text);
    }

    public static void debug(String text) {
        if (isDev()) {
            LOGGER.debug(MODID + " " + text);
        }
    }
}
