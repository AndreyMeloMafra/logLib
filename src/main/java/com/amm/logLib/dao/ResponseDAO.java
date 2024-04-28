package com.amm.logLib.dao;

import jakarta.servlet.http.HttpServletResponse;

public record ResponseDAO(String method, String path, String status) {

    public static ResponseDAO mount(HttpServletResponse httpServletResponse, RequestDAO requestDAO) {
        return new ResponseDAO(
                requestDAO.method(),
                requestDAO.path(),
                String.valueOf(httpServletResponse.getStatus())
        );
    }
}
