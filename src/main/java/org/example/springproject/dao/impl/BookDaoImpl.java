package org.example.springproject.dao.impl;

import org.example.springproject.dao.BookDao;
import org.example.springproject.exceptions.CommonSQLException;
import org.example.springproject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String FIND_ALL = "SELECT * FROM Book";
    private static final String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String SAVE_BOOK_WITH_AUTHOR_ID = "INSERT INTO book(author_id, title) VALUES (?, ?)";
    private static final String UPDATE_BOOK_TITLE = "UPDATE book SET title=? WHERE id=?";
    private static final String DELETE = "DELETE From book WHERE id=?";
    private static final String FIND_BOOK_BY_AUTHOR_ID = "SELECT * FROM book WHERE author_id=?";

    private final DataSource dataSource;

    @Autowired
    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();

            List<Book> bookList = new ArrayList<>();
            while (resultSet.next()) {
                bookList.add(buildBook(resultSet));
            }
            return bookList;
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public List<Book> findBooksByAuthorId(long authorId) {
        List<Book> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_AUTHOR_ID);
            statement.setLong(1, authorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(buildBook(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public Optional<Book> findBookById(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildBook(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public void save(Book book) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_BOOK_WITH_AUTHOR_ID);
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }

    @Override
    public void updateBookTitle(Book book, long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_TITLE);
            statement.setString(1, book.getTitle());
            statement.setLong(2, id);
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

    private Book buildBook(ResultSet resultSet) {
        try {
            String title = resultSet.getString("title");
            return new Book(title);

        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }
}
