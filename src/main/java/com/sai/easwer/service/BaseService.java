package com.sai.easwer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.model.Response;

public abstract class BaseService
{
    public ResponseEntity<Response> createResponse(String message, ResponseStatus status, Object object, HttpStatus httpStatus)
    {
        Response response = new Response();
        response.setMessage(message);
        response.setObject(object);
        response.setStatus(status);
        return new ResponseEntity<Response>(response, httpStatus);
    }
}