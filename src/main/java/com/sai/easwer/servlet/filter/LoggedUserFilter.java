package com.sai.easwer.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sai.easwer.security.CurrentSessionDetails;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-27 10:57:16
 * @desc [description]
 */
@Slf4j
@Component
public class LoggedUserFilter implements Filter {

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
            log.error("");
        } finally {
            CurrentSessionDetails.logout();
        }
    }

    @Override
    public void destroy() {
    }
}