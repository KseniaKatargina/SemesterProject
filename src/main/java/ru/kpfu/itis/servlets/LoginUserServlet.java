package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.FailedMessageService;
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
    private FailedMessageService failedMessageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        userRepository = (UserRepositoryDBImpl) getServletContext().getAttribute("userDAO");
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        failedMessageService = (FailedMessageService) getServletContext().getAttribute("failedService");
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


        if(!securityService.validate(userRepository, user)) {
            failedMessageService.setMessage("Неверно введен email или пароль",request,response);
            response.sendRedirect(path + "/FailedRegister");
        } else if(email.length() == 0 || password.length() == 0) {
            failedMessageService.setMessage("Заполните все поля",request,response);
            response.sendRedirect(path + "/FailedRegister");
        } else {
            User authUser;
            try {
                authUser = userRepository.userByEmail(email);
                userService.auth(authUser,request,response);
                response.sendRedirect(path + "/mainPage");
            } catch (DBException e) {
                throw  new ServletException(e);
            }
        }
    }
}
