package ru.kpfu.itis.listeners;
import ru.kpfu.itis.dao.ProductRepositoryDBImpl;
import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.services.*;
import ru.kpfu.itis.util.ConnectionProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            sce.getServletContext().setAttribute("userDAO", new UserRepositoryDBImpl(connectionProvider));
            sce.getServletContext().setAttribute("productDAO",new ProductRepositoryDBImpl(connectionProvider));
            sce.getServletContext().setAttribute("wishlistDAO", new WishlistRepositoryDBImpl(connectionProvider));
            sce.getServletContext().setAttribute("securityService", new SecurityService());
            sce.getServletContext().setAttribute("userService" , new UserService());
            sce.getServletContext().setAttribute("failedService", new FailedMessageService());
            sce.getServletContext().setAttribute("productService" , new ProductService());
            sce.getServletContext().setAttribute("wishlistService" , new WishlistService());
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

    }
}
