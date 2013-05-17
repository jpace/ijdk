package org.incava.ijdk.text;

import java.text.MessageFormat;

public class Message {
    private final String pattern;
    
    public Message(String pattern) {
        this.pattern = pattern;
    }

    public String format(Object ... args) {
        return MessageFormat.format(pattern, args);
    }

    public String getPattern() {
        return pattern;
    }
}
