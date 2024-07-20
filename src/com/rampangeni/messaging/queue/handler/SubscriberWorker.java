package com.rampangeni.messaging.queue.handler;

import com.rampangeni.messaging.queue.model.Subscriber;
import com.rampangeni.messaging.queue.model.Topic;
import com.rampangeni.messaging.queue.service.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriberWorker {
    public static Integer numberOfThreads = 2;
    List<Subscriber> subscriberList;
    private Topic topic;

    public SubscriberWorker(Topic topic) {
        this.subscriberList = new ArrayList<>();
        this.topic = topic;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscriberList.add(subscriber);
    }

    public void startSubscriberWorker() {
        ExecutorService executorService
                = Executors.newFixedThreadPool(numberOfThreads);

        subscriberList.forEach((item) -> {
            synchronized (item) {
                System.out.println("item: " + item.getName());
                int currentOffset = item.getMessageOffset().get();
                Message message = this.topic.readMessageOffsetFromTopic(currentOffset);
                if (message != null) {
                    executorService.submit(() -> {
                        try {
                            item.consumeMessage(message);
                            item.getMessageOffset().compareAndSet(currentOffset, currentOffset + 1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    System.out.println("No message to consume");
                }
            }
        });

        executorService.shutdown();
    }
}
