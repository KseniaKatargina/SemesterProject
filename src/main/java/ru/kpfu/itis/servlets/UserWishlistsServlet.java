package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Wishlist;
import ru.kpfu.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/myWishlists")
public class UserWishlistsServlet extends HttpServlet {
    private UserService userService;
    private WishlistRepositoryDBImpl wishlistRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        wishlistRepository = (WishlistRepositoryDBImpl) getServletContext().getAttribute("wishlistDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUser(request,response);
        try {
            ArrayList<Wishlist> wishlists =  wishlistRepository.getUsersLists(user);
            request.setAttribute("wishlists" , wishlists);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/userWishlists.jsp").forward(request, response);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
