package org.incava.ijdk.lang;

public interface Closure<ReturnType, ParamType> {
    public ReturnType execute(ParamType param);
}
