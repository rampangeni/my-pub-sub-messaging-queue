package com.rampangeni.messaging.queue.service;

import com.rampangeni.messaging.queue.model.Message;
import com.rampangeni.messaging.queue.model.Subscriber;
import com.rampangeni.messaging.queue.model.Topic;

import java.util.HashMap;

public class MessageQueue {

    private static MessageQueue instance;
    private HashMap<String,Topic> topics;
    private HashMap<String, Subscriber> subscribers;

    private MessageQueue() {
        topics = new HashMap<>();
        subscribers = new HashMap<>();
    }

    public static MessageQueue getMessageQueue() {
        if (instance == null) {
            synchronized (MessageQueue.class) {
                if (instance == null) {
                    instance = new MessageQueue();
                }
            }
        }

        return instance;
    }

    public String createTopic(String name) {
        if (this.topics.containsKey(name)) {
            throw new RuntimeException("Duplicate topic name");
        }

        Topic topic = new Topic(name);
        this.topics.put(name,topic);
        return name;
    }

    public void subscribeToTopic(String topicName, Subscriber subscriber) throws Exception {
        if (!topics.containsKey(topicName)) {
            throw new Exception("Topic doesn't exist");
        }

        if (subscriber == null) {
            throw new Exception("Subscriber is null");
        }

        subscribers.put(subscriber.getName(), subscriber);

        Topic topic = topics.get(topicName);
        topic.subscribeToTopic(subscriber);
    }

    public void publishToTopic(String topicName, Message message) throws Exception {
        if (!this.topics.containsKey(topicName)) {
            throw new Exception("Topic doesn't exist \n");
        }

        Topic topic = this.topics.get(topicName);
        topic.pushMessageToTopic(message);

        topic.startSubscriberWorker();
    }

    public Topic getTopicFromName(String name) {
        return this.topics.get(name);
    }

    public Subscriber getSubscriberFromName(String name) {
        return this.subscribers.get(name);
    }
}
