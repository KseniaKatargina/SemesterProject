package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/renameWishlist")
public class RenameWishlistServlet extends HttpServlet {
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
        long listID = Long.parseLong(request.getParameter("listID"));
        String title = request.getParameter("title");
        request.setAttribute("listID", listID);
        request.setAttribute("title", title);
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/renameWishlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUser(request,response);
        long userID = user.getId();

        long listID = Long.parseLong(request.getParameter("listID"));
        String title = request.getParameter("title");

        try {
            wishlistRepository.updateTitle(title, listID, userID);
            response.sendRedirect(request.getContextPath() + "/myWishlists");
        } catch (DBException e) {
           throw new RuntimeException(e);
        }

    }
}
