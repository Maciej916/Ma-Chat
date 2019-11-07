package com.maciej916.machat.data;

import com.google.gson.Gson;
import com.maciej916.machat.MaChat;
import com.maciej916.machat.classes.chat.ChatData;
import com.maciej916.machat.classes.rules.RulesData;
import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Log;
import com.maciej916.machat.libs.Methods;

import java.io.File;

import static com.maciej916.machat.libs.Methods.writeModFile;

public class DataLoader {

    public static void setupMain() {
        Log.log("Setup main catalog");
        ConfigValues.mainCatalog = System.getProperty("user.dir") + "/" + MaChat.MODID + "/";
        Log.debug("Main catalog is: " + ConfigValues.mainCatalog);
        try {
            Log.log("Creating main catalogs and files");
            new File(ConfigValues.mainCatalog).mkdirs();

            writeModFile("motd.txt", ConfigValues.mainCatalog + "motd.txt");
            writeModFile("rules.json", ConfigValues.mainCatalog + "rules.json");
            writeModFile("chat.json", ConfigValues.mainCatalog + "chat.json");

        } catch (Exception e) {
            Log.err("Error in setupMain");
            throw new Error(e);
        }
    }

    public static void load() {
        Log.log("Loading data");
        try {
            Log.debug("Clean data");
            DataManager.cleanData();
            Log.debug("Loading chat...");
            loadChat();
            Log.debug("Loading rules...");
            loadRules();
            Log.log("Data loaded");
        } catch (Exception e) {
            Log.err("Error while loading data!");
            throw new Error(e);
        }
    }

    private static void loadChat() throws Exception {
        ChatData data = new Gson().fromJson(Methods.loadFile(ConfigValues.mainCatalog, "chat"), ChatData.class);
        DataManager.setChat(data);
    }

    private static void loadRules() throws Exception {
        RulesData data = new Gson().fromJson(Methods.loadFile(ConfigValues.mainCatalog, "rules"), RulesData.class);
        DataManager.setRules(data);
    }

}
