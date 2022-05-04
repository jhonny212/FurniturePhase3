package com.furniture.inventoryService.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.net.http.HttpResponse;

public class HttpResponseSerializable extends ResponseEntity implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * Create a {@code ResponseEntity} with a body and status code.
     *
     * @param body   the entity body
     * @param status the status code
     */
    public HttpResponseSerializable(Object body, HttpStatus status) {
        super(body, status);
    }

    /**
     * Create a {@code ResponseEntity} with headers and a status code.
     *
     * @param headers the entity headers
     * @param status  the status code
     */
    public HttpResponseSerializable(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    /**
     * Create a {@code ResponseEntity} with a body, headers, and a status code.
     *
     * @param body    the entity body
     * @param headers the entity headers
     * @param status  the status code
     */
    public HttpResponseSerializable(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    /**
     * Create a {@code ResponseEntity} with a body, headers, and a raw status code.
     *
     * @param body      the entity body
     * @param headers   the entity headers
     * @param rawStatus the status code value
     * @since 5.3.2
     */
    public HttpResponseSerializable(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    /**
     * Create a {@code ResponseEntity} with a status code only.
     *
     * @param status the status code
     */
    public HttpResponseSerializable(HttpStatus status) {
        super(status);
    }
    public HttpResponseSerializable(){
        super(null,null);
    }
}
