package ijdk.lang;

import java.io.File;
import java.util.*;
import org.incava.ijdk.lang.ObjectExt;

/**
 * Extends java.io.File with Ruby-ish functionality.
 */
public class Pathname extends File {
    private static final long serialVersionUID = -4936465396173432030L;
    
    public Pathname(File file) {
        super(file.getPath());
    }

    public Pathname(String pathName) {
        super(pathName);
    }

    public String fullName() {
        return toString();
    }

    public String name() {
        return getName();
    }

    public File file() {
        return this;
    }
}
