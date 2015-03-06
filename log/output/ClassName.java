package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.StringExt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassName extends Item {    
    public ClassName(ANSIColor color, StackElements stackElements, int classWidth) {
        super(color, stackElements, classWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return StringExt.repeat(' ', width);
        }

        String className = stackElement.getClassName();        
        className = asConcise(className);
        return getSnipped(className);
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getClassName();
    }

    private String asConcise(String className) {
        // this used to be "(com|org)\\.\\w+\\.", "...", replacing only the top domain.
        return className.replaceAll("(\\w)\\w+\\.", "$1.");
    }
}
