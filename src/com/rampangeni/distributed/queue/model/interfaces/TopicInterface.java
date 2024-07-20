package com.rampangeni.distributed.queue.model.interfaces;


import com.rampangeni.distributed.queue.model.Subscriber;
import com.rampangeni.distributed.queue.service.Message;

import java.util.concurrent.atomic.AtomicInteger;

public interface TopicInterface {
    String getName();
    void pushMessageToTopic(Message message);
    void subscribeToTopic(Subscriber subscriber);
    Message readMessageOffsetFromTopic(Integer offset);
}
