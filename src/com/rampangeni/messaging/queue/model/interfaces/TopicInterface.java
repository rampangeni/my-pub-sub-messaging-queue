package com.rampangeni.messaging.queue.model.interfaces;


import com.rampangeni.messaging.queue.model.Subscriber;
import com.rampangeni.messaging.queue.model.Message;

public interface TopicInterface {
    String getName();
    void pushMessageToTopic(Message message);
    void subscribeToTopic(Subscriber subscriber);
    Message readMessageOffsetFromTopic(Integer offset);
}
