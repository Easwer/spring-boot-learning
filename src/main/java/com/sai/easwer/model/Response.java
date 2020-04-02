package com.sai.easwer.model;

import com.sai.easwer.constants.ResponseStatus;

/**
 * Response modal class for REST API.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:23
 */
public class Response {
    private String message;

    private Object object;

    private ResponseStatus status;

    /**
     * Get the message.
     * @return the message. {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * @param message the message to set. {@link String}
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get the response object.
     * @return the object. {@link String}
     */
    public Object getObject() {
        return object;
    }

    /**
     * Set the response object.
     * @param object the object to set. {@link String}
     */
    public void setObject(final Object object) {
        this.object = object;
    }

    /**
     * Get the response status.
     * @return the status. {@link String}
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * Set the response status.
     * @param status the status to set. {@link String}
     */
    public void setStatus(final ResponseStatus status) {
        this.status = status;
    }
}
