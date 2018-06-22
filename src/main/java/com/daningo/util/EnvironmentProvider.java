package com.daningo.util;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by naing on 6/21/18.
 */
public class EnvironmentProvider {
    public static final String DEVELOPMENT = "DEVELOPMENT";
    public static final String STAGING = "STAGING";
    public static final String PRODUCTION = "PRODUCTION";
    public static final String TEST = "TEST";

    private String environment = DEVELOPMENT;
    private static EnvironmentProvider environmentProvider;

    private EnvironmentProvider() {

    }

    public static EnvironmentProvider getEnvironmentProvider() {
        if(environmentProvider == null) {
            environmentProvider = new EnvironmentProvider();
            environmentProvider.setEnviroment();
        }
        return environmentProvider;
    }

    public void setEnviroment() {
        Logger logger = java.util.logging.Logger.getLogger(EnvironmentProvider.class.getSimpleName());
        String hostName = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
            logger.log(Level.INFO, "server hostname " + hostName);

            environment = PRODUCTION;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEnvironment() {
        return environment;
    }
}
