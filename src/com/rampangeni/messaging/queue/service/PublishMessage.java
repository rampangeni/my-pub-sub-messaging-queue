package com.rampangeni.messaging.queue.service;

import com.rampangeni.messaging.queue.model.Message;

public class PublishMessage implements Runnable {
    private String topic;
    private Message message;

    public PublishMessage(String topic, Message message) {
        this.topic = topic;
        this.message = message;
        new Thread(this).start();
    }

    @Override
    public void run() {
        MessageQueue queue = MessageQueue.getMessageQueue();
        try {
            System.out.println("[TO PUBLISH] Message: " + this.message.toString() + " Topic: " + this.topic);
            queue.publishToTopic(this.topic, this.message);
            System.out.println("[SUCCESS] Message: (" + this.message.toString() + ") published to Topic: " + this.topic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
