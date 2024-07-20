import com.rampangeni.distributed.queue.service.Message;
import com.rampangeni.distributed.queue.service.Queue;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        Queue queueService = Queue.getQueueService();

        String studentTopicId = queueService.createTopic("studentTopic");
        String teacherTopicId = queueService.createTopic("teacherTopic");

        String mathsTeacherSubscriberId = queueService.createSubscriber(
                "mathsTeacherSubscriber", 10000);
        String scienceTeacherSubscriberId = queueService.createSubscriber(
                "scienceTeacherSubscriber", 5000);

        queueService.subscribeToTopic(teacherTopicId, mathsTeacherSubscriberId);
        queueService.subscribeToTopic(teacherTopicId, scienceTeacherSubscriberId);

        String evsTeacherSubscriberId = queueService.createSubscriber(
                "evsTeacherSubscriber", 15000);
        queueService.subscribeToTopic(teacherTopicId, evsTeacherSubscriberId);

        Message teacherMessage1 = new Message(new Date().toString(), "Salary credited");
        queueService.publishToTopic(teacherTopicId, teacherMessage1);

        String class4StudentSubscriberId = queueService.createSubscriber(
                "class4StudentSubscriber", 10000);
        String class5StudentSubscriberId = queueService.createSubscriber(
                "class5StudentSubscriber", 10000);

        queueService.subscribeToTopic(studentTopicId, class4StudentSubscriberId);
        queueService.subscribeToTopic(studentTopicId, class5StudentSubscriberId);

        Message studentMessage = new Message(new Date().toString(), "Marks published");
        queueService.publishToTopic(studentTopicId, studentMessage);

        Message teacherMessage2 = new Message(new Date().toString(), "Be ready for PTM");
        queueService.publishToTopic(teacherTopicId, teacherMessage2);
    }
}