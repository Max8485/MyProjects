package org.example.springproject.dao.impl;

import jakarta.persistence.EntityManager;
import org.example.springproject.dao.BookDao;
import org.example.springproject.entity.Book;
import org.example.springproject.exceptions.CommonSQLException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    private static final String FIND_ALL_PAGEABLE = "SELECT * FROM book LIMIT ? OFFSET ?";

    private final DataSource dataSource;
    private final EntityManager entityManager;


    @Autowired
    public BookDaoImpl(DataSource dataSource, EntityManager entityManager) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findAll() { //entityManager сделать!
        Session session = entityManager.unwrap(Session.class); //работает!
        return session.createQuery("SELECT b FROM Book b", Book.class).getResultList();

//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
//            ResultSet resultSet = statement.executeQuery();
//
//            List<Book> bookList = new ArrayList<>();
//            while (resultSet.next()) {
//                bookList.add(buildBook(resultSet));
//            }
//            return bookList;
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    @Override
    public List<Book> findAllPageable(int pageSize, int pageNumber) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Book> query = session.createQuery("SELECT b FROM Book b LIMIT ? OFFSET ?", Book.class);
//        query.setParameter(1, pageSize);
//        query.setParameter(2, pageNumber);
//        return query.getResultList();


        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAGEABLE);
            statement.setInt(1, pageSize);
            statement.setInt(2, pageNumber);
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
    public List<Book> findBooksByAuthorId(long authorId) { //работает!
        Session session = entityManager.unwrap(Session.class);
        Query<Book> query = session.createQuery("SELECT b FROM Book b WHERE b.author.id=:id", Book.class);
        query.setParameter("id", authorId);
        return query.getResultList();

//        List<Book> result = new ArrayList<>();
//
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_AUTHOR_ID);
//            statement.setLong(1, authorId);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                result.add(buildBook(resultSet));
//            }
//            return result;
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    @Override
    public Optional<Book> findBookById(long id) { //работает!
        Session session = entityManager.unwrap(Session.class);
        Query<Book> query = session.createQuery("SELECT b FROM Book b WHERE b.id=:id", Book.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());

//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_ID);
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                return Optional.of(buildBook(resultSet));
//            }
//            return Optional.empty();
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    @Override
    @Transactional
    public void save(Book book, long authorId) { //работает!
        entityManager.createNativeQuery(SAVE_BOOK_WITH_AUTHOR_ID)
                .setParameter(1, authorId)
                .setParameter(2, book.getTitle())
                .executeUpdate();

//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(SAVE_BOOK_WITH_AUTHOR_ID);
//            statement.setLong(1, authorId);
//            statement.setString(2, book.getTitle());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    @Override
    @Transactional
    public void updateBookTitle(Book book, long id) { //работает!
        entityManager.createNativeQuery(UPDATE_BOOK_TITLE)
                .setParameter(1, book.getTitle())
                .setParameter(2, id)
                .executeUpdate();

//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_TITLE);
//            statement.setString(1, book.getTitle());
//            statement.setLong(2, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    @Override
    @Transactional
    public void delete(long id) { //работает!
        entityManager.createNativeQuery(DELETE)
                .setParameter(1, id)
                .executeUpdate();

//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(DELETE);
//            statement.setLong(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new CommonSQLException(e);
//        }
    }

    private Book buildBook(ResultSet resultSet) {
        try {
            return Book.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .build();
        } catch (SQLException e) {
            throw new CommonSQLException(e);
        }
    }
}
