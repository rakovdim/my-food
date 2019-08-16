package com.myfood.commons.utils.ids;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDIdGenerator implements IdGenerator<UUID> {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }
}
