package core.productcatalogcache.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Incoming Request: Method={} URI={} QueryString={}",
                request.getMethod(), request.getRequestURI(), request.getQueryString());

        filterChain.doFilter(request, response);

        log.info("Outgoing Response: Status={}", response.getStatus());
    }
}
