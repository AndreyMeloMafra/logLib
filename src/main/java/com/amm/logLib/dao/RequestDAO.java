package com.amm.logLib.dao;


import jakarta.servlet.http.HttpServletRequest;

public record RequestDAO(String method, String path, String requestId, String params) {
    public static RequestDAO mount(HttpServletRequest httpServletRequest) {
        return new RequestDAO(
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getHeader("X-Request-Id"),
                httpServletRequest.getQueryString()
        );
    }
}