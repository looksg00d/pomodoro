package ru.itis.servlets;

import ru.itis.repository.NoteRepositoryImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteNote")
public class DeleteNoteServlet extends HttpServlet {
    private NoteRepositoryImpl noteRepository;

    @Override
    public void init() {
        this.noteRepository = new NoteRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long noteId = Long.valueOf(req.getParameter("noteId"));
        noteRepository.deleteById(noteId);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
