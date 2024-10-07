/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.configuration;

import com.fresh.filter.NoSessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<NoSessionFilter> noSessionFilter() {
        FilterRegistrationBean<NoSessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new NoSessionFilter());
        registrationBean.addUrlPatterns("/public/check.xhtml");
        return registrationBean;
    }
}

