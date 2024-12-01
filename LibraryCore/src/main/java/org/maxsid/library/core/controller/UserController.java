package org.maxsid.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.service.ApplicationUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ApplicationUserMapper mapper;
    private final ApplicationUserService userService;


    @PostMapping("/api/v1/account") //работает!
    public void saveUser(@RequestBody ApplicationUserDto userDto) { //принимает dto, маппает в applicationUser и сохраняет!
        ApplicationUser user = mapper.toUser(userDto);
        userService.saveUser(user);
    }

//    @PostMapping("/api/v1/account")
//    public ResponseEntity<HttpStatus> saveUser(@RequestBody ApplicationUserDto userDto) {
//        ApplicationUser user = mapper.toUser(userDto);
//        userService.saveUser(user);
//     return ResponseEntity.ok(HttpStatus.OK);
//    }
}
