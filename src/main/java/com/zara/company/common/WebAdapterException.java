package com.zara.company.common;

import java.util.Map;

public class WebAdapterException extends GenericException {

    private static final long serialVersionUID = -1737538110921725875L;

    public WebAdapterException(Class<?> clasz, String method, Map<String, Object> params, String message) {
        super(clasz, method, params, message);
    }
}

