package ru.itis.servlets;

import ru.itis.model.Note;
import ru.itis.model.Visitor;
import ru.itis.repository.NoteRepositoryImpl;
import ru.itis.service.VisitorService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private VisitorService visitorService;
    private NoteRepositoryImpl noteRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        visitorService = (VisitorService) config.getServletContext().getAttribute("VisitorService");
        noteRepository = new NoteRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long visitorId = (Long) req.getSession().getAttribute("visitorid");

        if (visitorId != null) {
            Visitor visitor = visitorService.getVisitorById(visitorId).orElse(null);
            List<Note> notes = noteRepository.findByVisitorId(visitorId);

            req.setAttribute("user", visitor);
            req.setAttribute("notes", notes);
            req.getRequestDispatcher("profile.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
