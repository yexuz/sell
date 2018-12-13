package com.imooc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luiz
 * @Title: FilterConfig
 * @ProjectName gpay-commons
 * @Description: TODO
 * @date 2018-12-12 17:41
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MDCLogWebFilter());
        registration.addUrlPatterns("/*");
        registration.setName("mdcWebFilter");
        registration.setOrder(1);
        return registration;

    }
}
