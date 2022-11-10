package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.HashService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {
    private UserRepositoryDBImpl userRepository;
    private UserService userService;
    private SecurityService securityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        userRepository = (UserRepositoryDBImpl) getServletContext().getAttribute("userDAO");
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashPassword = HashService.hashPassword(password);

        String path = String.valueOf(request.getContextPath());

        User user = new User(email,hashPassword);

        HttpSession session = request.getSession();

        if(!securityService.validate(userRepository, user)) {
            session.setAttribute("message", "Неверно введен email или пароль");
            response.sendRedirect(path + "/FailedRegister");
        } else if(email.length() == 0 || password.length() == 0) {
            session.setAttribute("message", "Заполните все поля");
            response.sendRedirect(path + "/FailedRegister");
        } else {
            User authUser;
            try {
                authUser = userRepository.userByEmail(email);
                userService.auth(authUser,request,response);
                response.sendRedirect(path + "/mainPage");
            } catch (DBException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
