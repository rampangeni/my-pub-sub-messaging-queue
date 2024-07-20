package com.rampangeni.distributed.queue.model;

import com.rampangeni.distributed.queue.service.Message;

import java.util.UUID;

public class Data {
    final private String id;

    final private Message message;

    public String getId() {
        return this.id;
    }

    public Data(Message message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }
}
