package com.daningo;

import com.daningo.modules.UIMyBatisModule;
import com.daningo.service.MessageService;
import com.daningo.service.TopicService;
import com.daningo.service.UIService;
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
import org.restlet.security.User;
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
            DaningoAPIInjector.init(Guice.createInjector(new UIMyBatisModule(isTest)));
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
        router.attach("/user/", UserService.class);
        router.attach("/user/{userID}", UserService.class);
        router.attach("/message/get/{topicID}/{messageID}", MessageService.class);
        router.attach("/user/{userID}/topics", TopicService.class);
        router.attach("/user/{userID}/topics/{topicID}",TopicService.class);
        router.attach("/user/{userID}/topics/{topicID}/messages/{messageID}", MessageService.class);


        //challengeAuthenticator.setNext(router);
        //CorsFilter corsFilter = new CorsFilter(getContext(), challengeAuthenticator);
        //return corsFilter;
        return router;
    }
}
