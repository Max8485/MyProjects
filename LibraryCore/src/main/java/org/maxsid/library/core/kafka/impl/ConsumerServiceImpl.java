package org.maxsid.library.core.kafka.impl;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.kafka.ConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @KafkaListener(topics = "user-registration", groupId = "core-service")
    @Override
    public void consumeMessage(ApplicationUserDto userDto) {
        System.out.println("------------------------------------------");
        System.out.println(userDto);
        System.out.println("------------------------------------------");
    }
}
