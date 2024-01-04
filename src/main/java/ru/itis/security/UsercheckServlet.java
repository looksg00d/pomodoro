package ru.itis.security;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.itis.model.Visitor;
import ru.itis.service.VisitorService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/usercheck")
public class UsercheckServlet extends HttpServlet {

    private VisitorService visitorService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        visitorService = (VisitorService) getServletContext().getAttribute("VisitorService");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<Visitor> visitorOptional = visitorService.findByUsername(username);

        if (visitorOptional.isPresent()) {
            if (visitorOptional.get().getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("visitorname", visitorOptional.get().getName());
                session.setAttribute("visitorid", visitorOptional.get().getVisitorId());
                response.sendRedirect("/pomodoro/main");
            } else {
                request.setAttribute("message", "Incorrect password");
                request.getRequestDispatcher("login.ftl").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Username not found");
            request.getRequestDispatcher("login.ftl").forward(request, response);
        }
    }
}
