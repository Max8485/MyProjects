package org.maxsid.library.core.kafka.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.service.ApplicationUserService;
import org.maxsid.library.dto.ApplicationUserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl {

    private final ApplicationUserMapper mapper;
    private final ApplicationUserService userService;

    //@Payload используется для указания параметра, который должен быть заполнен полезной нагрузкой сообщения при получении сообщения Kafka.

    //MANUAL_IMMEDIATE — это режим подтверждения (ack-mode) в Kafka, при котором смещение фиксируется немедленно,
    // как только слушатель вызывает метод Acknowledgment.acknowledge().

    @KafkaListener(topics = "user-registration", groupId = "core-service")
    public void consumeMessage(@Payload ApplicationUserDto userDto, Acknowledgment acknowledgment) {
        ApplicationUser user = mapper.toUser(userDto);

        userService.saveUser(user);

        acknowledgment.acknowledge(); //Это означает, что сообщение успешно обработано и может быть зафиксировано (commit offset)
    }
}
