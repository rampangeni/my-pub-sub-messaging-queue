package com.rampangeni.messaging.queue.service;

import com.rampangeni.messaging.queue.model.Subscriber;
import com.rampangeni.messaging.queue.model.Topic;

import java.util.HashMap;

public class MessageQueue {

    private static MessageQueue instance;
    private HashMap<String,Topic> topics;
    private HashMap<String,Subscriber> subscribers;

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

    public String createSubscriber(String name, Integer sleepTime) {
        if (this.subscribers.containsKey(name)) {
            throw new RuntimeException("Duplicate subscriber name");
        }

        Subscriber subscriber = new Subscriber(name, sleepTime);
        this.subscribers.put(name,subscriber);
        return name;
    }

    public void subscribeToTopic(String topicName, String subscriberName) throws Exception {
        if (!subscribers.containsKey(subscriberName)) {
            throw new Exception("Subscriber doesn't exist \n");
        }

        if (!topics.containsKey(topicName)) {
            throw new Exception("Topic doesn't exist \n");
        }

        Topic topic = topics.get(topicName);
        Subscriber subscriber = subscribers.get(subscriberName);
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
