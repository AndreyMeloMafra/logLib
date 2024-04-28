package com.amm.logLib.config;

import com.amm.logLib.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestFilter> loggingFilter(){
        FilterRegistrationBean<RequestFilter> registrationBean
                = new FilterRegistrationBean<>();

        HashMap<String, String> infos = new HashMap<>();

        infos.put("info1", "value1");
        infos.put("info2", "value2");

        registrationBean.setFilter(new RequestFilter(infos));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}