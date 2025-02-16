package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT count(b) from Book b where b.user.id=:id")
    Long countBooksByUserId(Long id);

    @Query(value = "SELECT exists (select 1 from Book b where b.id=:id and b.user.id is null)")
    Boolean isAvailable(Long id);

    @Modifying
    @Query(value = "UPDATE book set user_id=:userId where id=:bookId", nativeQuery = true)
    void updateUserIdForBook(Long userId, Long bookId);
}
