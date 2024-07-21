package com.rampangeni.messaging.queue.service;

import com.rampangeni.messaging.queue.model.Subscriber;

import java.util.HashSet;

public class SubscriberService {
    private HashSet<String> subscribers;

    public SubscriberService() {
        subscribers = new HashSet<>();
    }

    public Subscriber createSubscriber(String name, Integer sleepTime) {
        if (subscribers.contains(name)) {
            throw new RuntimeException("Duplicate subscriber name");
        }

        subscribers.add(name);
        return new Subscriber(name, sleepTime);
    }
}
