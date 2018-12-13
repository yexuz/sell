package com.imooc.config;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;
import java.util.UUID;

/**
 * @author luiz
 * @Title: MDCLogWebFilter
 * @ProjectName gpay-commons
 * @Description: TODO
 * @date 2018-12-12 17:32
 */
public class MDCLogWebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("UUID", UUID.randomUUID().toString());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
