package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.ProductRepositoryDBImpl;
import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Wishlist;
import ru.kpfu.itis.services.ProductService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishlistService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/mainPage")
public class MainPageServlet extends HttpServlet {
    private ProductRepositoryDBImpl productRepository;
    private UserService userService;
    private WishlistRepositoryDBImpl wishlistRepository;
    private ProductService productService;
    private WishlistService wishlistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        wishlistRepository = (WishlistRepositoryDBImpl) getServletContext().getAttribute("wishlistDAO");
        productRepository = (ProductRepositoryDBImpl) getServletContext().getAttribute("productDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
        productService = (ProductService) getServletContext().getAttribute("productService");
        wishlistService = (WishlistService) getServletContext().getAttribute("wishlistService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Product> products = productRepository.getAllProducts();
            productService.addProducts(products,request,response);
            User user = userService.getUser(request,response);
            ArrayList<Wishlist> wishlists = wishlistRepository.getUsersLists(user);
            wishlistService.addWishlists(wishlists,request,response);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
