package com.v7878.sun.cleaner;

import static com.v7878.misc.Version.CORRECT_SDK_INT;

import android.os.Build;

@SuppressWarnings("StaticInitializerReferencesSubClass")
public abstract sealed class SunCleaner permits LatestCleaner, LegacyCleaner {
    private static final SunCleaner INSTANCE;

    static {
        if (CORRECT_SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            INSTANCE = LatestCleaner.INSTANCE;
        } else {
            INSTANCE = LegacyCleaner.INSTANCE;
        }
    }

    SunCleaner() {
    }

    public static SunCleaner systemCleaner() {
        return INSTANCE;
    }

    public abstract Cleanable register(Object obj, Runnable action);

    public interface Cleanable {
        void clean();
    }
}
