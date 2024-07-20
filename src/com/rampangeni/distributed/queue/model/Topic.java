package com.rampangeni.distributed.queue.model;

import com.rampangeni.distributed.queue.handler.SubscriberWorker;
import com.rampangeni.distributed.queue.model.interfaces.TopicInterface;
import com.rampangeni.distributed.queue.service.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic implements TopicInterface {
    private final String name;
    HashMap<String,Data> dataCollection;
    List<Message> messages;
    SubscriberWorker subscriberWorker;

    public Topic(String name) {
        this.name = name;
        messages = new ArrayList<>();
        dataCollection = new HashMap<>();
        subscriberWorker = new SubscriberWorker(this);
    }

    public String getName() {
        return this.name;
    }

    public void pushMessageToTopic(Message message) {
        Data data = new Data(message);
        this.messages.add(message);
        this.dataCollection.put(data.getId(), data);
    }

    public void subscribeToTopic(Subscriber subscriber) {
        this.subscriberWorker.addSubscriber(subscriber);
    }

    public Message readMessageOffsetFromTopic(Integer currentOffset) {
        while (currentOffset >= this.messages.size()) {
            return null;
        }

        return this.messages.get(currentOffset);
    }

    public void startSubscriberWorker() {
        this.subscriberWorker.startSubscriberWorker();
    }
}
