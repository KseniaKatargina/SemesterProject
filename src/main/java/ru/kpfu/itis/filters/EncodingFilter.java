package ru.kpfu.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "isExistUserFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override public void init(FilterConfig config) {}
    @Override public void destroy() {}
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        chain.doFilter(req, res);
    }
}