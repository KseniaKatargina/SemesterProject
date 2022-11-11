package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.ProductRepositoryDBImpl;
import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.ProductService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishlistService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
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
        User user = userService.getUser(request,response);
        long userID = user.getId();

        String listID = request.getParameter("listID");
        String title;

        try {
            title = wishlistRepository.getTitle( Long.parseLong(listID));
            wishlistService.addTitle(title,request,response);
            wishlistService.addID(Long.parseLong(listID),request,response);

            ArrayList<Long> productsID = productRepository.getListProductsID(userID,Long.parseLong(listID));

            ArrayList<Product> products = new ArrayList<>();
            for (long id : productsID){
                products.add(productRepository.getProducts(id));
            }

            productService.addProducts(products,request,response);

            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/wishlist.jsp").forward(request, response);
        } catch (DBException e) {
            throw  new ServletException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
