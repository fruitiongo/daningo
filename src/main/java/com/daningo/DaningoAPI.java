package com.daningo;

import com.daningo.modules.UIMyBatisModule;
import com.daningo.service.*;
import com.daningo.util.DaningoAPIInjector;

import com.google.inject.Guice;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
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
        //router.attach("/message/get/{topicID}/{messageID}", MessageService.class);
        router.attach("/user/{userID}/topic/{topicID}/message/{messageID}", MessageService.class);
        router.attach("/user/{userID}/topics", TopicService.class);
        router.attach("/user/{userID}/topics/{topicID}",TopicService.class);
        router.attach("/user/{userID}/topics/{topicID}/messages/{messageID}", MessageService.class);
        router.attach("/user/{userID}/rating", UserRatingService.class);
        router.attach("/user/{userID}/topic/{topicID}/rating" , UserRatingService.class);


        //router.attach("/topics",TopicService.class);
       // router.attach("/topic/{topicID}/messages",)


        // create payment api && get
        //router.attach("/user/{userID}/payment", UserPaymentService.class);

        router.attach("/user/{userID}/topic/{topicID}/payment", TransactionService.class);


        // user sign up
        router.attach("/signup", AuthService.class);
        // user password reset
        //router.attach("/password/email/{email}", RecoverService.class);
        // user sign in
        router.attach("/signin/{email}/{password}", AuthService.class);
        // device id match for sign in


        router.attach("/upload", UploadService.class);
        // notification for both android and ios
        // image upload
        // instagram integration && twitch integration

        // reminder for influencer
        //

        //challengeAuthenticator.setNext(router);
        //CorsFilter corsFilter = new CorsFilter(getContext(), challengeAuthenticator);
        //return corsFilter;
        return router;
    }
}
