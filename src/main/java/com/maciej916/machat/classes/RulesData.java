package com.maciej916.machat.classes;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Json;

import java.util.ArrayList;

public class RulesData {

    public String format_top;
    public String format_rule;
    public String format_bottom;

    ArrayList<String> rules = new ArrayList<>();

    public void addRule(String rule) {
        rules.add(rule);
        saveData();
    }

    public void removeRule(int index) {
        rules.remove(index);
        saveData();
    }

    public ArrayList<String> rulesList() {
        return rules;
    }

    public void saveData() {
        Json.save(ConfigValues.mainCatalog, "rules", this);
    }
}
