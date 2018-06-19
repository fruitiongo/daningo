package com.daningo.com.daningo.util;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.security.Verifier;

public class UITokenVerifier implements Verifier {
    @Override
    public int verify(Request request, Response response) {
        return 0;
    }
}
