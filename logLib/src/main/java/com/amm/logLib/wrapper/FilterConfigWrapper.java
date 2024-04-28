package com.amm.logLib.wrapper;

import com.amm.logLib.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.HashMap;

public class FilterConfigWrapper {

    private HashMap<String, String> infos;

    public FilterConfigWrapper() {
        this.infos = new HashMap<>();
    }

    public void addInfo(String key, String value) {
        this.infos.put(key, value);
    }

    public FilterRegistrationBean<RequestFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter(this.infos));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}