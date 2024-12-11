package org.maxsid.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.service.ApplicationUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
