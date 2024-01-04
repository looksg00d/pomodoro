package ru.itis.repository;

import ru.itis.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    List<Note> getAll();
    Optional<Note> findById(Long id);
    boolean deleteById(Long id);
    void save(Note note);
    List<Note> findByVisitorId(Long visitorId);
}
