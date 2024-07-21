package com.rampangeni.messaging.queue.model;

import jdk.nashorn.internal.objects.annotations.Getter;

public class Message {
    final private String key;
    final private String value;

    public Message(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.key + " -> " + this.value;
    }
}