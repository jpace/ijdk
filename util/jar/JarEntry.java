package org.incava.ijdk.util.jar;

import java.io.File;
import java.util.jar.JarOutputStream;
import org.incava.ijdk.io.FileExt;

public class JarEntry {
    private final File file;
    private final String name;

    public JarEntry(File file, String name) {
        this.file = file;
        this.name = name;
    }

    public JarEntry(File file) {
        this(file, file.getName());
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public void writeToJarStream(JarOutputStream jos) throws Exception {
        java.util.jar.JarEntry je = new java.util.jar.JarEntry(name);
        je.setTime(file.lastModified());
        jos.putNextEntry(je);

        byte[] contents = FileExt.readBytes(file);
        jos.write(contents);
    }
}
