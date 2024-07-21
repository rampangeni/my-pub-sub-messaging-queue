import com.rampangeni.messaging.queue.model.Message;
import com.rampangeni.messaging.queue.service.PublishMessage;
import com.rampangeni.messaging.queue.model.Subscriber;
import com.rampangeni.messaging.queue.service.MessageQueue;
import com.rampangeni.messaging.queue.service.SubscriberService;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        /* Message Queue */
        MessageQueue messageQueue = MessageQueue.getMessageQueue();

        /* Create Topic */
        String studentTopic = messageQueue.createTopic("studentTopic");
        String teacherTopic = messageQueue.createTopic("teacherTopic");

        /* Create Subscriber */
        SubscriberService subscriberService = new SubscriberService();
        Subscriber mathsTeacherSubscriber = subscriberService.createSubscriber(
                "mathsTeacherSubscriber", 10000);
        Subscriber scienceTeacherSubscriber = subscriberService.createSubscriber(
                "scienceTeacherSubscriber", 5000);
        Subscriber evsTeacherSubscriber = subscriberService.createSubscriber(
                "evsTeacherSubscriber", 15000);
        Subscriber class4StudentSubscriberId = subscriberService.createSubscriber(
                "class4StudentSubscriber", 10000);
        Subscriber class5StudentSubscriberId = subscriberService.createSubscriber(
                "class5StudentSubscriber", 10000);

        /* Subscribe to Topic */
        messageQueue.subscribeToTopic(teacherTopic, mathsTeacherSubscriber);
        messageQueue.subscribeToTopic(teacherTopic, scienceTeacherSubscriber);
        messageQueue.subscribeToTopic(teacherTopic, evsTeacherSubscriber);
        messageQueue.subscribeToTopic(studentTopic, class4StudentSubscriberId);
        messageQueue.subscribeToTopic(studentTopic, class5StudentSubscriberId);

        /* Messages */
        Message teacherMessage1 = new Message(new Date().toString(), "Salary credited");
        Message teacherMessage2 = new Message(new Date().toString(), "Be ready for PTM");
        Message studentMessage = new Message(new Date().toString(), "Marks published");

        /* Start Publishing Messages */
        PublishMessage publishMessage1 = new PublishMessage(teacherTopic, teacherMessage1);
        PublishMessage publishMessage2 = new PublishMessage(teacherTopic, teacherMessage2);
        PublishMessage publishMessage3 = new PublishMessage(studentTopic, studentMessage);
    }
}