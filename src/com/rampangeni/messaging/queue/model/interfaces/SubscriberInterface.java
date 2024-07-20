package com.rampangeni.messaging.queue.model.interfaces;

import com.rampangeni.messaging.queue.service.Message;

import java.util.concurrent.atomic.AtomicInteger;

public interface SubscriberInterface {
    AtomicInteger getMessageOffset();
    void consumeMessage(Message message) throws InterruptedException;
}
