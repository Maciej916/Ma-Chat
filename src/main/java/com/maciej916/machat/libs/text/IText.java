package com.maciej916.machat.libs.text;

import net.minecraft.util.text.ITextComponent;

public interface IText {

    String getText();
    ITextComponent getTextComponent();

    void setText(String text);
}
