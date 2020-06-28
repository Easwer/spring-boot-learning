package com.sai.easwer.servlet.filter;

import com.sai.easwer.security.CurrentSessionDetails;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Servlet filter to get user details.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-27 10:57:16
 */
@Slf4j
@Component
public class CustomServletFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {

        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            CurrentSessionDetails.login(httpServletRequest.getRemoteUser());

            filterChain.doFilter(request, response);
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            CurrentSessionDetails.logout();
        }
    }

    @Override
    public void destroy() {
    }
}
