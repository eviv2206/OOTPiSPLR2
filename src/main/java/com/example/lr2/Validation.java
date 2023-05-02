package com.example.lr2;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Validation {
    public static UnaryOperator<TextFormatter.Change> getFilter() {
        Pattern pattern = Pattern.compile("[0-9]*");
        return change -> {
            String text = change.getControlNewText();
            if (pattern.matcher(text).matches()) {
                return change;
            } else {
                return null;
            }
        };
    }
}