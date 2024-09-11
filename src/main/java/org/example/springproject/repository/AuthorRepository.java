package org.example.springproject.repository;

import org.example.springproject.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Override
    @Query(value = "SELECT author from Author author LEFT JOIN FETCH author.books WHERE author.id=:id")
    Optional<Author> findById(Long id);

    @Query(value = "SELECT author FROM Author author JOIN FETCH author.books")
    Page<Author> findAllWithBooks(Pageable pageable);

    @Query(value = """
            SELECT EXISTS(
            SELECT 1 FROM author 
            WHERE first_name=:firstName and last_name=:lastName and middle_name=:middleName and date_of_birth=:dateOfBirth
            )
                        """, nativeQuery = true)
    boolean  existsByUniqueIndex(String firstName, String lastName, String middleName, LocalDate dateOfBirth);

}
