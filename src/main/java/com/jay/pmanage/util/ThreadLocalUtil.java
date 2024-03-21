package com.jay.pmanage.util;

public class ThreadLocalUtil {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }

    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    public static void clear(){
        THREAD_LOCAL.remove();
    }
}
