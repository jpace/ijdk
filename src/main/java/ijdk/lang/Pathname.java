package ijdk.lang;

import java.io.File;
import java.util.*;
import org.incava.ijdk.lang.ObjectExt;

public class Pathname {
    private final File file;
    private final String fileName;
    
    public Pathname(File file) {
        this.file = file;
        this.fileName = null;
    }

    public Pathname(String fileName) {
        this.file = null;
        this.fileName = fileName;
    }

    public String name() {
        return fileName;
    }

    public File file() {
        return file;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pathname) {
            Pathname other = (Pathname)obj;
            return ObjectExt.areEqual(fileName, other.name()) && ObjectExt.areEqual(file, other.file());
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return (file == null ? 1 : file.hashCode()) * 37 + (fileName == null ? 1 : fileName.hashCode());
    }
}
