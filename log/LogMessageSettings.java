package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Settings for logging messages.
 */
public class LogMessageSettings {
    public int fileWidth = 25;
    public int lineWidth = 5;
    public int functionWidth = 25;
    public int classWidth = 35;

    public boolean showFiles = true;
    public boolean showClasses = true;

    public boolean useColumns = true;
}
