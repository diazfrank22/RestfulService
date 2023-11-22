package com.zara.company.common;

import java.util.Map;

public class GenericException extends RuntimeException {

    private static final long serialVersionUID = 2405172041950251807L;

    private final Class<?> clasz;

    private final String method;

    private final transient Map<String, Object> params;

    public GenericException(Class<?> clasz, String method, Map<String, Object> params, String message) {
        super(message);
        this.clasz = clasz;
        this.method = method;
        this.params = params;
    }

}