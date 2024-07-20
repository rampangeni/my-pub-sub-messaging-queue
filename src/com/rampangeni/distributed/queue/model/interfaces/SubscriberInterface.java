package com.rampangeni.distributed.queue.model.interfaces;

import com.rampangeni.distributed.queue.service.Message;

import java.util.concurrent.atomic.AtomicInteger;

public interface SubscriberInterface {
    AtomicInteger getMessageOffset();
    void consumeMessage(Message message) throws InterruptedException;
}
