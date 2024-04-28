package com.amm.logLib.filter;

import com.amm.logLib.dao.RequestDAO;
import com.amm.logLib.dao.ResponseDAO;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;


@AllArgsConstructor
public class RequestFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(RequestFilter.class.getName());
    private HashMap<String, String> infos;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        RequestDAO requestDAO = RequestDAO.mount(httpServletRequest);
        ResponseDAO responseDAO = ResponseDAO.mount(httpServletResponse, requestDAO);

        LOGGER.info(mountRequestLogMessage(requestDAO));
        LOGGER.info(mountResponseLogMessage(responseDAO));
    }

    private String mountRequestLogMessage(RequestDAO requestDAO) {
        StringBuilder logStringBuilder = new StringBuilder();

        appendPart(logStringBuilder, "Method", requestDAO.method());
        appendPart(logStringBuilder, "Path", requestDAO.path());
        appendPart(logStringBuilder, "RequestId", requestDAO.requestId());
        appendPart(logStringBuilder, "Params", requestDAO.params());

        infos.forEach((key, value) -> appendPart(logStringBuilder, key, value));

        return logStringBuilder.toString();
    }

    private String mountResponseLogMessage(ResponseDAO responseDAO) {
        StringBuilder logStringBuilder = new StringBuilder();

        appendPart(logStringBuilder, "Method", responseDAO.method());
        appendPart(logStringBuilder, "Path", responseDAO.path());
        appendPart(logStringBuilder, "Status", responseDAO.status());

        return logStringBuilder.toString();
    }

    private void appendPart(StringBuilder builder, String label, String value) {
        if (StringUtils.isBlank(value)) {
            return;
        }

        if (!builder.isEmpty()) {
            builder.append(" | ");
        }
        builder.append(label).append(": ").append(value);
    }
}