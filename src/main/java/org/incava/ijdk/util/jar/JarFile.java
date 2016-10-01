package org.incava.ijdk.util.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.*;

import org.incava.ijdk.util.IUtil;

public class JarFile {
    private final String name;
    private final List<JarEntry> entries;
    private final Map<String, String> attributes;

    public JarFile(String name, JarEntry ... entries) {
        this.name = name;
        this.entries = new ArrayList<JarEntry>(Arrays.asList(entries));
        this.attributes = new HashMap<String, String>();
    }

    public void addFile(File file) {
        addEntry(new JarEntry(file));
    }

    public void addFile(File file, String name) {
        addEntry(new JarEntry(file, name));
    }

    public void addEntry(JarEntry entry) {
        entries.add(entry);
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void write() throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(name));
        Manifest man = new Manifest();
        Attributes manattrs = man.getMainAttributes();
        for (Map.Entry<String, String> attr : attributes.entrySet()) {
            manattrs.put(attr.getKey(), attr.getValue());
        }
        JarOutputStream jos = new JarOutputStream(fos, man);
        for (JarEntry entry : entries) {
            entry.writeToJarStream(jos);
        }
        jos.close();
        fos.close();
    }
}
