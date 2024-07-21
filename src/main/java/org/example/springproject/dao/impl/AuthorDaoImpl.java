package org.example.springproject.dao.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.exceptions.CommonSQLException;
import org.example.springproject.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private static final String FIND_ALL = "SELECT * FROM author";
    private static final String FIND_ALL_WITH_BOOKS = "SELECT * FROM author JOIN book on author.id = book.author_id" +
            " ORDER BY author.id";
    private static final String FIND_AUTHOR_BY_ID = "SELECT * FROM author WHERE id=?";
    private static final String SAVE = "INSERT INTO author(first_name, last_name, middle_name, date_of_birth) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR_NAME = "UPDATE author SET first_name=?, last_name=?, middle_name=?," +
            " date_of_birth=? WHERE id=?";
    private static final String DELETE = "DELETE From author WHERE id=?";

    private final DataSource dataSource;

    @Autowired
    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Author> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();

            List<Author> authorList = new ArrayList<>();
            while (resultSet.next()) {
                authorList.add(buildAuthor(resultSet));
            }
            return authorList;
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public List<Author> findAllWithBooks() { // доработать!
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_BOOKS);
            ResultSet resultSet = statement.executeQuery();

            List<Author> authorListWithBooks = new ArrayList<>();
            while (resultSet.next()) {
                authorListWithBooks.add(buildAuthor(resultSet));
            }
            return authorListWithBooks;
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public Optional<Author> findAuthorById(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_AUTHOR_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildAuthor(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public void save(Author author) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getMiddleName());
            statement.setDate(4, Date.valueOf(author.getDateOfBirth()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public void updateAuthorName(Author author, long id) { //работает!
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_NAME);
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getMiddleName());
            statement.setDate(4, Date.valueOf(author.getDateOfBirth()));
            statement.setLong(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    private Author buildAuthor(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String middleName = resultSet.getString("middle_name");
            LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
            return new Author(id, firstName, lastName, middleName, dateOfBirth, new ArrayList<>());
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }
}
