package com.maciej916.machat.data;

import com.maciej916.machat.classes.chat.ChatData;
import com.maciej916.machat.classes.rules.RulesData;

public class DataManager {

    private static ChatData chatData = new ChatData();
    private static RulesData rulesData = new RulesData();

     public static void cleanData() {
         rulesData = new RulesData();
     }

    public static RulesData getRules() {
        return rulesData;
    }

    public static void setRules(RulesData rulesData) {
        DataManager.rulesData = rulesData;
    }

    public static ChatData getChat() {
        return chatData;
    }

    public static void setChat(ChatData chatData) {
        DataManager.chatData = chatData;
    }
}
