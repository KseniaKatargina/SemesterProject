package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.ProductRepositoryDBImpl;
import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/removeProduct")
public class RemoveProductServlet extends HttpServlet {
    private ProductRepositoryDBImpl productRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productRepository = (ProductRepositoryDBImpl) getServletContext().getAttribute("productDAO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productID = Long.valueOf(request.getParameter("prodID"));
        Long listID = Long.valueOf(request.getParameter("listID"));
        try {
            productRepository.removeProductFromList(productID,listID);
            response.sendRedirect(request.getContextPath() + "/myWishlists");
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }
}

