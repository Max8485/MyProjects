package org.maxsid.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.service.ApplicationUserService;
import org.maxsid.library.dto.ApplicationUserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ApplicationUserMapper mapper;
    private final ApplicationUserService userService;


    @PostMapping("/api/v1/account")
    public void saveUser(@RequestBody ApplicationUserDto userDto) { //принимает dto, маппает в applicationUser и сохраняет!
        ApplicationUser user = mapper.toUser(userDto);
        userService.saveUser(user);
    }

    @PostMapping("/api/v1/account/book/{book_id}")
    public void takeBook(@PathVariable(name = "book_id") Long bookId) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.takeBook(login, bookId);
    }


    @DeleteMapping("/api/v1/account/book/{book_id}")
    public void returnBook(@PathVariable(name = "book_id") long bookId) {
        userService.returnBook(bookId);
    }
}
