package ru.kpfu.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/mainPage","/profile","/addProduct", "/addWishlist"})
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if(session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/welcome");
            return;
        }
        chain.doFilter(req,res);
    }
}
