package org.example.springproject.dao.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.exceptions.CommonSQLException;
import org.example.springproject.entity.Author;
import org.example.springproject.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.*;
import java.util.*;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private static final String FIND_ALL = "SELECT author.id AS author_id, author.first_name, author.last_name, " +
            "author.middle_name, author.date_of_birth FROM author";

    private static final String FIND_ALL_WITH_BOOKS = "SELECT author.first_name, author.last_name, author.middle_name, \n" +
            "             author.date_of_birth, book.id AS book_id, author.id AS author_id, book.title\n" +
            "             FROM author LEFT JOIN book on author.id = book.author_id ORDER BY author.id";

    private static final String FIND_AUTHOR_BY_ID = "SELECT author.id AS author_id, author.first_name, author.last_name, " +
            "author.middle_name, author.date_of_birth FROM author WHERE id=?";

    private static final String SAVE = "INSERT INTO author(first_name, last_name, middle_name, date_of_birth) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR_NAME = "UPDATE author SET first_name=?, last_name=?, middle_name=?," +
            " date_of_birth=? WHERE id=?";
    private static final String DELETE = "DELETE From author WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM author";
    private static final String EXISTS_BY_AUTHOR_ID = "SELECT EXISTS(SELECT 1 FROM author WHERE id=?)";

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
    public List<Author> findAllWithBooks() {
        Map<Long, Author> authors = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_BOOKS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long authorId = resultSet.getLong("author_id");
                Author currentAuthor;
                if (!authors.containsKey(authorId)) {
                    currentAuthor = buildAuthor(resultSet);
                    authors.put(authorId, currentAuthor);
                    currentAuthor.setBooks(new ArrayList<>());
                } else {
                    currentAuthor = authors.get(authorId);
                }
                currentAuthor.getBooks().add(buildBook(resultSet));
            }
            return new ArrayList<>(authors.values());
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
    public Author save(Author author) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getMiddleName());
            statement.setDate(4, Date.valueOf(author.getDateOfBirth()));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getLong(1));
            }
            return author;
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

    @Override
    public void deleteAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    private Author buildAuthor(ResultSet resultSet) {
        try {
            return Author.builder()
                    .id(resultSet.getLong("author_id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .middleName(resultSet.getString("middle_name"))
                    .dateOfBirth(resultSet.getDate("date_of_birth").toLocalDate())
                    .books(new ArrayList<>())
                    .build();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }
    private Book buildBook(ResultSet resultSet) {
        try {
            return Book.builder()
                    .title(resultSet.getString("title"))
                    .id(resultSet.getLong("book_id"))
                    .build();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public boolean existsById(long authorId) { //работает!
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(EXISTS_BY_AUTHOR_ID);
            statement.setLong(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }
}
