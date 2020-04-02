package com.sai.easwer.service;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Base service.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:07:18
 */
@Component
public abstract class BaseService {

    /**
     * This method is used to create {@link ResponseEntity}<{@link Response}> for all REST API's.
     * 
     * @param message {@link String}
     * @param status {@link ResponseStatus}
     * @param object {@link Object}
     * @param httpStatus {@link HttpStatus}
     * @return REST API Response as {@link ResponseEntity}<{@link Response}>
     */
    public ResponseEntity<Response> createResponse(final String message,
            final ResponseStatus status, final Object object, final HttpStatus httpStatus) {
        final Response response = new Response();
        response.setMessage(message);
        response.setObject(object);
        response.setStatus(status);
        return new ResponseEntity<Response>(response, httpStatus);
    }
}
