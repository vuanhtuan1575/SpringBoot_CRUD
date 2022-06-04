package com.vnpay.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * It is to check if there is a {@code RequestRejectedException}
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestRejectedExceptionFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(RequestRejectedExceptionFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        try {
            chain.doFilter(req, res);
        } catch (RequestRejectedException e) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            LOG.error("RequestRejectedException : userAgent: {}, requestUrl: {}",
                    request.getHeader(HttpHeaders.USER_AGENT), request.getRequestURL(), e);
             response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }

}

