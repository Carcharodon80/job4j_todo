package ru.job4j.todo.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
@Order(1)
public class AuthorizationFilter extends HttpFilter {
    private static final Set<String> MAPPINGS = Set.of("login", "registration", "all", "done", "undone");

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (isPermitted(uri)) {
            chain.doFilter(request, response);
            return;
        }
        boolean userLoggedIn = request.getSession().getAttribute("user") != null;
        if (!userLoggedIn) {
            String loginPage = request.getContextPath() + "/users/login";
            response.sendRedirect(loginPage);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isPermitted(String uri) {
        return MAPPINGS.stream().anyMatch(uri::endsWith);
    }
}
