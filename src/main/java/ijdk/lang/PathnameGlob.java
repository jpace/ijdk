package ijdk.lang;

import java.io.File;
import java.util.List;

public class PathnameGlob {
    public static final String SEPARATOR = File.separator;

    public static String toPattern(String glob) {
        String[] components = new Str(glob).split(SEPARATOR);
        List<String> patList = ICore.<String>list();
        for (String comp : components) {
            patList.add(elementToPattern(comp));
        }
        return Str.join(patList, SEPARATOR).str();
    }

    /**
     * Converts an element within a glob, to a pattern.
     */
    protected static String elementToPattern(String element) {
        if (element.equals("**")) {
            return ".*";
        }
        else {
            return element.replaceAll("\\*", "[^\\" + SEPARATOR + "]*").replaceAll("\\.", "\\\\.");
        }
    }
}
