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

@WebServlet("/addWishlist")
public class AddWishlistServlet extends HttpServlet {
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
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addWishlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String path = String.valueOf(request.getContextPath());

        User user = userService.getUser(request,response);
        long id = user.getId();
        Wishlist wishlist = new Wishlist(id,title);

        if(title.length() == 0){
            request.getSession().setAttribute("message", "Название не может быть пустым");
            response.sendRedirect(path + "/FailedCreate");
        } else {
            try {
                wishlistRepository.addList(wishlist);
                response.sendRedirect(path + "/myWishlists");
            } catch (DBException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
