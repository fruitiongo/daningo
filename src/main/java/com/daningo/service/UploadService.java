package com.daningo.service;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;

public class UploadService extends BaseService {
    @Post
    public Representation doPost( Representation entity) {
        String responseJson = "{}";
        if(entity != null && MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(),true)) {


        } else {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Error");

        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
