package ru.itis.repository;

import ru.itis.model.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;


public class NoteRepositoryImpl implements NoteRepository {

    @Override
    public List<Note> getAll() {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM notes");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                notes.add(extractNoteFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notes;
    }

    @Override
    public Optional<Note> findById(Long id) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM notes WHERE note_id = ?")) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractNoteFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM notes WHERE note_id = ?")) {

            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Note note) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (visitor_id, content) VALUES (?, ?)")) {

            preparedStatement.setLong(1, note.getVisitorId());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Note> findByVisitorId(Long visitorId) {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM notes WHERE visitor_id = ?")) {

            preparedStatement.setLong(1, visitorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    notes.add(extractNoteFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notes;
    }

    private Note extractNoteFromResultSet(ResultSet resultSet) throws SQLException {
        Long noteId = resultSet.getLong("note_id");
        Long visitorId = resultSet.getLong("visitor_id");
        String content = resultSet.getString("content");
        Timestamp creationDate = resultSet.getTimestamp("creation_date");

        return new Note(noteId, visitorId, content, creationDate);
    }
}
