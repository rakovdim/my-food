package com.myfood.commons.utils.ids;

import java.util.UUID;

public class UUIDIdGenerator implements IdGenerator<UUID> {

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }
}
