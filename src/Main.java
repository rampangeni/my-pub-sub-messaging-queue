import com.rampangeni.messaging.queue.service.Message;
import com.rampangeni.messaging.queue.service.MessageQueue;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        MessageQueue messageQueue = MessageQueue.getMessageQueue();

        String studentTopicId = messageQueue.createTopic("studentTopic");
        String teacherTopicId = messageQueue.createTopic("teacherTopic");

        String mathsTeacherSubscriberId = messageQueue.createSubscriber(
                "mathsTeacherSubscriber", 10000);
        String scienceTeacherSubscriberId = messageQueue.createSubscriber(
                "scienceTeacherSubscriber", 5000);

        messageQueue.subscribeToTopic(teacherTopicId, mathsTeacherSubscriberId);
        messageQueue.subscribeToTopic(teacherTopicId, scienceTeacherSubscriberId);

        String evsTeacherSubscriberId = messageQueue.createSubscriber(
                "evsTeacherSubscriber", 15000);
        messageQueue.subscribeToTopic(teacherTopicId, evsTeacherSubscriberId);

        Message teacherMessage1 = new Message(new Date().toString(), "Salary credited");
        messageQueue.publishToTopic(teacherTopicId, teacherMessage1);

        String class4StudentSubscriberId = messageQueue.createSubscriber(
                "class4StudentSubscriber", 10000);
        String class5StudentSubscriberId = messageQueue.createSubscriber(
                "class5StudentSubscriber", 10000);

        messageQueue.subscribeToTopic(studentTopicId, class4StudentSubscriberId);
        messageQueue.subscribeToTopic(studentTopicId, class5StudentSubscriberId);

        Message studentMessage = new Message(new Date().toString(), "Marks published");
        messageQueue.publishToTopic(studentTopicId, studentMessage);

        Message teacherMessage2 = new Message(new Date().toString(), "Be ready for PTM");
        messageQueue.publishToTopic(teacherTopicId, teacherMessage2);
    }
}