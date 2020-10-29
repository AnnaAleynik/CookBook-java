package ru.itis.aleynik.cookingbook.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class Filter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String userAg = req.getHeader("User-Agent");

        if (userAg.contains("Edg")) {
            req.getRequestDispatcher("/WEB-INF/views/edge.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }

    }
}
