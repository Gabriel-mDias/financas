package br.com.gems.base;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {

    public static final String REQUEST_BODY_ATTRIBUTE = "request-body";

    public void setBodyToExceptionLog( Object bodyObject, HttpServletRequest request ) {
        request.setAttribute( this.REQUEST_BODY_ATTRIBUTE, bodyObject );
    }

}
