package com.v7878.sun.cleaner;

import android.annotation.SuppressLint;

import java.lang.ref.Cleaner;

@SuppressLint("NewApi")
final class LatestCleaner extends SunCleaner {
    public static final SunCleaner INSTANCE = new LatestCleaner();
    private final Cleaner cleaner;

    private LatestCleaner() {
        this.cleaner = Cleaner.create();
    }

    @Override
    public Cleanable register(Object obj, Runnable action) {
        return cleaner.register(obj, action)::clean;
    }
}
