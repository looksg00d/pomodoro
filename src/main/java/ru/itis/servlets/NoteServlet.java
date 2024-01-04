package ru.itis.servlets;

import ru.itis.model.Note;
import ru.itis.repository.NoteRepositoryImpl;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {
    private NoteRepositoryImpl noteRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.noteRepository = new NoteRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long visitorId = (Long) req.getSession().getAttribute("visitorid");
        String content = req.getParameter("content");

        if (visitorId != null && content != null && !content.trim().isEmpty()) {
            Note newNote = new Note();
            newNote.setVisitorId(visitorId);
            newNote.setContent(content);

            noteRepository.save(newNote);
            System.out.println("Note added successfully.");
            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            System.out.println("Error: Missing visitorId or content.");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
