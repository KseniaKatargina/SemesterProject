package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Wishlist;
import ru.kpfu.itis.services.FailedMessageService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishlistService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addWishlist")
public class AddWishlistServlet extends HttpServlet {
    private UserService userService;
    private WishlistRepositoryDBImpl wishlistRepository;
    private FailedMessageService failedMessageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        wishlistRepository = (WishlistRepositoryDBImpl) getServletContext().getAttribute("wishlistDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
        failedMessageService = (FailedMessageService) getServletContext().getAttribute("failedService");
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
            failedMessageService.setMessage("Название не может быть пустым",request,response);
            response.sendRedirect(path + "/FailedCreateWishlist");
        } else {
            try {
                wishlistRepository.addList(wishlist);
                response.sendRedirect(path + "/myWishlists");
            } catch (DBException e) {
                throw new ServletException(e);
            }
        }

    }
}
