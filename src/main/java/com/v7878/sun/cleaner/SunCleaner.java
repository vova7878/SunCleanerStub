package com.v7878.sun.cleaner;

import static android.os.Build.VERSION.SDK_INT;

import android.os.Build.VERSION_CODES;

@SuppressWarnings("StaticInitializerReferencesSubClass")
public abstract sealed class SunCleaner permits LatestCleaner, LegacyCleaner {
    private static final SunCleaner INSTANCE = SDK_INT >= VERSION_CODES.TIRAMISU ?
            LatestCleaner.INSTANCE : LegacyCleaner.INSTANCE;

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
