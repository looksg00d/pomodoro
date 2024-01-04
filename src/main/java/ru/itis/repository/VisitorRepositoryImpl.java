package ru.itis.repository;

import ru.itis.model.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitorRepositoryImpl implements VisitorRepository {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM visitors";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM visitors WHERE visitor_id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM visitors WHERE visitor_id = ?";
    private static final String FIND_BY_USERNAME = "SELECT * FROM visitors WHERE username = ?";
    private static final String INSERT_QUERY = "INSERT INTO visitors (name, email, username, password, phone_number) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Visitor> getAll() {
        List<Visitor> visitors = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Long id = resultSet.getLong("visitor_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String phoneNumber = resultSet.getString("phone_number");
                    visitors.add(new Visitor(id, name, email, username, password, phoneNumber));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DBConnection.getInstance().releaseConnection(connection);
            }
        }
        return visitors;
    }
    @Override
    public Optional<Visitor> findByUsername(String username) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Long id = resultSet.getLong("visitor_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String phoneNumber = resultSet.getString("phone_number");
                    return Optional.of(new Visitor(id, name, email, username, password, phoneNumber));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DBConnection.getInstance().releaseConnection(connection);
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<Visitor> findById(Long id) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String phoneNumber = resultSet.getString("phone_number");
                    return Optional.of(new Visitor(id, name, email, username, password, phoneNumber));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DBConnection.getInstance().releaseConnection(connection);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
                preparedStatement.setLong(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DBConnection.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void save(Visitor visitor) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setString(1, visitor.getName());
                preparedStatement.setString(2, visitor.getEmail());
                preparedStatement.setString(3, visitor.getUsername());
                preparedStatement.setString(4, visitor.getPassword());
                preparedStatement.setString(5, visitor.getPhoneNumber());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DBConnection.getInstance().releaseConnection(connection);
            }
        }
    }


}
