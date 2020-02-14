import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sai.easwer.security.LoggedInUser;;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
public class LoggedUserFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {

        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            LoggedInUser.login(httpServletRequest.getRemoteUser());

            filterChain.doFilter(request, response);
        } finally {
            LoggedInUser.logout();
        }
    }

    @Override
    public void destroy() {
    }
}