package ru.itis.service;

import ru.itis.model.Visitor;
import ru.itis.repository.VisitorRepository;
import ru.itis.repository.VisitorRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class VisitorService {
    private VisitorRepository visitorRepository;
    public VisitorService() {
        visitorRepository = new VisitorRepositoryImpl();
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.getAll();
    }

    public Optional<Visitor> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    public boolean deleteVisitorById(Long id) {
        return visitorRepository.deleteById(id);
    }

    public void saveVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }
    public Optional<Visitor> findByUsername(String username) {
        return visitorRepository.findByUsername(username);
    }

}