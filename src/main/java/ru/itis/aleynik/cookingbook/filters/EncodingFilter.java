package ru.itis.aleynik.cookingbook.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf8");
        res.setCharacterEncoding("utf8");
        chain.doFilter(req, res);
    }
}
