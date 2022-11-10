package ru.kpfu.itis.filters;

import ru.kpfu.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = { "/mainPage","/profile","/addProduct", "/addWishlist","/editProfile","/exit","/failedAddProduct","/FailedCreateWishlist","/failedEditProfile",
//        "/removeProduct", "/removeWishlist","/renameWishlist","/myWishlists","/wishlist"})
//public class AuthFilter extends HttpFilter {
//
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpSession session = req.getSession();
//
//        if(session == null || session.getAttribute("user") == null) {
//            res.sendRedirect(req.getContextPath() + "/welcome");
//            return;
//        }
//        chain.doFilter(req,res);
//    }
//}

@WebFilter("/*")
public class AuthFilter extends HttpFilter{
    private static final String[] securityPages = new String[]{ "/mainPage","/profile","/addProduct", "/addWishlist","/editProfile","/exit","/failedAddProduct","/FailedCreateWishlist","/failedEditProfile",
        "/removeProduct", "/removeWishlist","/renameWishlist","/myWishlists","/wishlist"};
    private UserService userService;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean protect = false;
        for(String path:securityPages) {
            if(path.equals(req.getRequestURI().substring(req.getContextPath().length()))){
                protect = true;
                break;
            }
        }
        if(protect && !userService.isAuth(req,res)){
            res.sendRedirect(req.getContextPath() + "/welcome");
        } else {
            if(userService.isAuth(req,res)){
                req.setAttribute("user",userService.getUser(req,res));
            }
            chain.doFilter(req,res);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");

    }
}
