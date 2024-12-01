package org.maxsid.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.entity.ApplicationUserAccount;
import org.maxsid.library.auth.service.LibraryCoreService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LibraryCoreServiceImpl implements LibraryCoreService {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    @Override
    public void sendToCore(ApplicationUserDto userDto) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8080/api/v1/account";

        HttpEntity<ApplicationUserDto> request = new HttpEntity<>(userDto, headers); //создаем тело запроса

        restTemplate.postForObject(url, request, Void.class); //отправляем тело запроса
    }
}
