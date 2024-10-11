package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
