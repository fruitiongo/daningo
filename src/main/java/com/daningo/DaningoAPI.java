package com.daningo;

import com.daningo.service.UserService;
import com.daningo.util.UITokenVerifier;
import com.daningo.util.DaningoAPIInjector;

import com.google.inject.Guice;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import com.daningo.db.DBPropertiesProvider;

import java.util.logging.Logger;
import com.google.gson.Gson;
import org.restlet.service.CorsService;

public class DaningoAPI extends Application {

    DBPropertiesProvider prodDB;

    public static Gson gson;
    private Logger logger;

    public DaningoAPI() {
        this(false);
    }

    public DaningoAPI(boolean isTest) {

        if(DaningoAPIInjector.getInjector() == null){
            DaningoAPIInjector.init(Guice.createInjector( ));
        }

        CorsService corsService = new CorsService();
        getServices().add(corsService);
    }

    @Override
    public synchronized Restlet createInboundRoot() {
        //ChallengeAuthenticator challengeAuthenticator = new ChallengeAuthenticator(getContext(),ChallengeScheme.CUSTOM.HTTP_OAUTH_BEARER,"realm");
        //challengeAuthenticator.setVerifier(new UITokenVerifier());
        Router router = new Router(getContext());

        //Router path
        router.attach("/user/info", UserService.class);

        //challengeAuthenticator.setNext(router);
        //CorsFilter corsFilter = new CorsFilter(getContext(), challengeAuthenticator);
        //return corsFilter;
        return router;
    }
}
