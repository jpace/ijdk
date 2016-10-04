package org.incava.ijdk.util.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class OptionList {
    public List<String> args;
    private List<OptionSet> optionSets;
    private int idx;

    public OptionList() {
        optionSets = new ArrayList<OptionSet>();
    }

    public void addOptionSet(OptionSet os) {
        optionSets.add(os);
    }

    public boolean processOptionSets() {
        for (OptionSet os : optionSets) {
            if (os.checkOptions(this)) {
                return true;
            }
        }
        return false;
    }
    
    public void process(List<String> args) {
        this.args = new ArrayList<String>(args);
        idx = 0;
        while (idx < this.args.size()) {
            if (!processOptionSets() && !process()) {
                ++idx;
            }
        }
    }

    public abstract boolean process();

    public String current() {
        return args.get(idx);
    }

    public List<String> args() {
        return args;
    }

    public boolean match(List<String> strs) {
        if (strs.contains(current())) {
            pop();
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean match(String ... strs) {
        List<String> list = Arrays.asList(strs);
        return match(list);
    }

    public boolean match(String str) {
        if (current().equals(str)) {
            pop();
            return true;
        }
        else {
            return false;
        }
    }
    
    public String pop() {
        return args.remove(idx);
    }

    public Integer popAsInteger() {
        String arg = pop();
        return Integer.valueOf(arg);
    }

    public Integer currentAsInteger() {
        String arg = current();
        return Integer.valueOf(arg);
    }

    public boolean checkOptions(OptionSet os) {
        return os.checkOptions(this);
    }
}
