package com.daningo.util;

import com.google.inject.Injector;

/**
 * Created by naing on 6/21/18.
 */
public class DaningoAPIInjector {

    private static Injector instance = null;
    public static void init(Injector injector) {
        instance = injector;
    }

    public static Injector getInjector() {
        return instance;
    }
}
