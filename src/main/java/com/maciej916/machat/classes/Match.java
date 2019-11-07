package com.maciej916.machat.classes;

public class Match {

    public static final String MATCH_VARIABLE = "variable";
    public static final String MATCH_TEXT = "text";
    public static final String MATCH_FORMATTED = "formatted";

    private Object var;
    private String type;

    public Match(Object var, String type) {
        this.var = var;
        this.type = type;
    }

    public Object getVar() {
        return var;
    }

    public String getType() {
        return type;
    }

    public String getString() {
        return (String) var;
    }

}
