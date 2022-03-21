package be.nicholas.shop.core.filter;

import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.web.internal.RequestTelemetryContext;
import com.microsoft.applicationinsights.web.internal.ThreadContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component


public class TelemetryUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestTelemetryContext threadContext = ThreadContext.getRequestTelemetryContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (threadContext != null) {
            RequestTelemetry requestTelemetry = threadContext.getHttpRequestTelemetry();
            requestTelemetry.getContext().getUser().setId(username);
        }

        chain.doFilter(request, response);
    }
}
