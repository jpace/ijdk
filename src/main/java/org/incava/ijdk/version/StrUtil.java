package org.incava.ijdk.version;

public class StrUtil {
    public static Integer getNumber(String[] strs, Integer index, Integer defValue) {
        return strs.length > index && strs[index].length() > 0 ? Integer.valueOf(strs[index]) : defValue;
    }

    public static Integer getNumber(Integer[] ints, Integer index, Integer defValue) {
        return ints.length > index ? ints[index] : defValue;
    }

    public static StringBuilder append(StringBuilder sb, Integer value, boolean prependDot) {
        if (value == null) {
            return sb;
        }
        else if (prependDot) {
            sb.append('.');
        }
        return sb.append(value);
    }
}
