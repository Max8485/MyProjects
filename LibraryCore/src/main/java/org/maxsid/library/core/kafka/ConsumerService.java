package org.maxsid.library.core.kafka;


import org.maxsid.library.core.dto.ApplicationUserDto;

public interface ConsumerService {
    void consumeMessage(ApplicationUserDto userDto);
}
