package ijdk.lang;

import java.util.*;

public class PathnameGlob {
    public PathnameGlob() {
    }

    public static String toPattern(String glob) {
        String separator = "/";
        String[] components = new Str(glob).split(separator);
        List<String> patList = new ArrayList<String>();
        for (String comp : components) {
            if (comp.equals("**")) {
                patList.add(".*");
            }
            else {
                String pat = comp.replaceAll("\\*", "[^\\" + separator + "]*").replaceAll("\\.", "\\\\.");
                patList.add(pat);
            }
        }
        String pattern = Str.join(patList, separator).str();
        return pattern;
    }
}
