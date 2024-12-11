package org.maxsid.library.auth.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.kafka.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
public class KafkaController {

//    private final ProducerService producerService;
//
//    @PostMapping("/api/v1/kafkaMessage")
//    public void messageToTopic(@RequestBody ApplicationUserDto message) {
//        this.producerService.sendMessage(message);
//    }
}
