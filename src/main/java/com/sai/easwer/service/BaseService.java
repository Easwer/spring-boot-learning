package com.sai.easwer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.model.Response;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
public abstract class BaseService {
    public ResponseEntity<Response> createResponse(final String message, final ResponseStatus status,
            final Object object, final HttpStatus httpStatus) {
        final Response response = new Response();
        response.setMessage(message);
        response.setObject(object);
        response.setStatus(status);
        return new ResponseEntity<Response>(response, httpStatus);
    }
}