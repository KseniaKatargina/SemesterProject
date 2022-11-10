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
import java.rmi.RemoteException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("prodID");
        String wishlistID = request.getParameter("listID");
        long listID = Integer.parseInt(wishlistID);
        long prodID = Integer.parseInt(productID);

        String path = String.valueOf(request.getContextPath());

        Wishlist wishlist = new Wishlist(listID);

        try {
            if(wishlistRepository.isProductInList(wishlist, prodID)){
                request.getSession().setAttribute("message", "Продукт уже есть на это листе");
                response.sendRedirect(path + "/failedAddProduct");
            } else {
                wishlistRepository.addProductInListEntry(listID,prodID);
                response.sendRedirect(path + "/mainPage");
            }
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }
}
