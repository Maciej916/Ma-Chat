package com.maciej916.machat.libs.text;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class VariableText implements IText {
    private String text;

    public VariableText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public ITextComponent getTextComponent() {
        return new StringTextComponent(text);
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}