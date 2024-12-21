package org.maxsid.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.service.LibraryCoreService;
import org.maxsid.library.dto.ApplicationUserDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "serviceCommunication", havingValue = "async")
public class LibraryCoreServiceAsync implements LibraryCoreService {

    private final KafkaTemplate<String, ApplicationUserDto> kafkaTemplate;

    @Override
    public void sendToCore(ApplicationUserDto userDto) {
        kafkaTemplate.send("user-registration", userDto);
    }
}
