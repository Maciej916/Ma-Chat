package com.maciej916.machat.classes;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Json;

import java.util.ArrayList;

public class MessagesData {

    private int frequency = 60000;
    private ArrayList<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
        saveData();
    }

    public void removeMessage(int index) {
        messages.remove(index);
        saveData();
    }

    public ArrayList<String> messagesList() {
        return messages;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        saveData();
    }

    public void saveData() {
        Json.save(ConfigValues.mainCatalog, "messages", this);
    }
}
