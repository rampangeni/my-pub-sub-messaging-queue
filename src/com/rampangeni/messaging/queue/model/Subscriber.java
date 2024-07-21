package com.rampangeni.messaging.queue.model;

import com.rampangeni.messaging.queue.model.Message;
import com.rampangeni.messaging.queue.model.interfaces.SubscriberInterface;

import java.util.concurrent.atomic.AtomicInteger;

public class Subscriber implements SubscriberInterface {
    final private String name;
    final private AtomicInteger messageOffset;
    final private Integer subscriberSleepTime;

    public Subscriber(String name, Integer sleepTime) {
        this.name = name;
        this.messageOffset = new AtomicInteger(0);
        this.subscriberSleepTime = sleepTime;
    }

    public String getName() {
        return this.name;
    }

    public AtomicInteger getMessageOffset() {
        return this.messageOffset;
    }

    @Override
    public synchronized void consumeMessage(Message message) throws InterruptedException {
        System.out.println("Message Offset: " + this.messageOffset);
        System.out.println("Subscriber " + this.name + " is consuming the message");
        System.out.println("Message:: " + message.getKey() + " -> " + message.getValue());
        System.out.println("Subscriber consumed the message");
        Thread.sleep(this.subscriberSleepTime);
    }
}
