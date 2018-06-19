package com.daningo;

import com.daningo.com.daningo.util.UITokenVerifier;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;

public class DaningoAPI extends Application {
    @Override
    public synchronized Restlet createInboundRoot() {
        ChallengeAuthenticator challengeAuthenticator = new ChallengeAuthenticator(getContext(),ChallengeScheme.CUSTOM.HTTP_OAUTH_BEARER,"realm");
        challengeAuthenticator.setVerifier(new UITokenVerifier());
        Router router = new Router(getContext());
        challengeAuthenticator.setNext(router);
        CorsFilter corsFilter = new CorsFilter(getContext(), challengeAuthenticator);
        return corsFilter;
    }
}
