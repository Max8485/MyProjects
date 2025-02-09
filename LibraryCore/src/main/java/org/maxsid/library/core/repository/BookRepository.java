package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT count(b) from Book b where b.user.id=:id")
    Long countBooksByUserId(Long id);

    @Query(value = "SELECT exists (select 1 from Book b where b.id=:id and b.user.id is null)")
    Boolean isAvailable(Long id);

    @Modifying
    @Query(value = "UPDATE book set user_id=:userId where id=:bookId", nativeQuery = true)
    void updateUserIdForBook(Long userId, Long bookId);

    //UPDATE book set user_id=61 where id=2;
    // @Query(value = "UPDATE Book b set b.user.id=:id where b.id=:id")

    //SELECT exists(select 1 from book where id=50 and user_id is null);

    //@Query(value = "SELECT b.user.id from Book b where b.id=:id")


}
