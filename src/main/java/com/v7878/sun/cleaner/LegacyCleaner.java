package com.v7878.sun.cleaner;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

final class LegacyCleaner extends SunCleaner {
    public static final SunCleaner INSTANCE = new LegacyCleaner();

    private static final MethodHandle CREATE;
    private static final MethodHandle CLEAN;

    static {
        try {
            Class<?> cleaner = Class.forName("sun.misc.Cleaner");
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            CREATE = lookup.findStatic(cleaner, "create",
                    MethodType.methodType(cleaner, Object.class, Runnable.class));
            CLEAN = lookup.findVirtual(cleaner, "clean",
                    MethodType.methodType(void.class));
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private LegacyCleaner() {
    }

    @Override
    public Cleanable register(Object obj, Runnable action) {
        Object cleaner;
        try {
            cleaner = CREATE.invoke(obj, action);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return () -> {
            try {
                CLEAN.invoke(cleaner);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
