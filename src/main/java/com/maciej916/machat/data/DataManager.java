package com.maciej916.machat.data;

import com.maciej916.machat.classes.ChatData;
import com.maciej916.machat.classes.MessagesData;
import com.maciej916.machat.classes.RulesData;

public class DataManager {

    private static ChatData chatData = new ChatData();
    private static RulesData rulesData = new RulesData();
    private static MessagesData messagesData = new MessagesData();

    public static void cleanData() {
        chatData = new ChatData();
        rulesData = new RulesData();
        messagesData = new MessagesData();
     }

    public static RulesData getRules() {
        return rulesData;
    }

    public static ChatData getChat() {
        return chatData;
    }

    public static MessagesData getMessages() {
        return messagesData;
    }


    public static void setRules(RulesData rulesData) {
        DataManager.rulesData = rulesData;
    }

    public static void setChat(ChatData chatData) {
        DataManager.chatData = chatData;
    }

    public static void setMessages(MessagesData messagesData) {
        DataManager.messagesData = messagesData;
    }

}
