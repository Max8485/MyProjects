package org.maxsid.library.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.exceptions.BookNotFoundException;
import org.maxsid.library.core.exceptions.LimitBooksForUserException;
import org.maxsid.library.core.repository.ApplicationUserRepository;
import org.maxsid.library.core.repository.BookRepository;
import org.maxsid.library.core.service.ApplicationUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void saveUser(ApplicationUser user) {
        if (!userRepository.existsByLogin(user.getLogin())) {
            userRepository.save(user);
        } else {
            log.warn("Catch duplicate {}", user.getLogin());
        }
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void takeBook(String login, Long bookId) {
        Boolean available = bookRepository.isAvailable(bookId);
        if (available) {
            Optional<Long> userIdOpt = userRepository.findUserIdByLogin(login);
            if (userIdOpt.isPresent() && bookRepository.countBooksByUserId(userIdOpt.get()) < 3) { //вынести в application.yaml
                bookRepository.updateUserIdForBook(userIdOpt.get(), bookId);
            } else {
                throw new LimitBooksForUserException();
            }

        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void returnBook(Long bookId) { //TODO Макс хотел что-то рассказать
        bookRepository.findById(bookId).ifPresent(book -> {
            book.setUser(null);
            bookRepository.save(book);
        });
    }
}
