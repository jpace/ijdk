package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.StringExt;

public class Message extends Item {
    private final String message;
    
    public Message(ANSIColorList colors, StackElements stackElements, String msg) {
        super(colors, stackElements, null);
        this.message = StringExt.chomp(msg);
    }

    public boolean isRepeated() {
        return false;
    }

    public Object getValue(StackTraceElement stackElement) {
        return message;
    }

    public boolean snipIfLong() {
        return false;
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return null;
    }
}
