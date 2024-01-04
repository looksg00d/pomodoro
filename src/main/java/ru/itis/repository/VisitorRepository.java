package ru.itis.repository;

import ru.itis.model.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository {
    public List<Visitor> getAll();
    public Optional<Visitor> findById(Long id);
    public boolean deleteById(Long id);
    public void save(Visitor visitor);
    public Optional<Visitor> findByUsername(String username);
}
