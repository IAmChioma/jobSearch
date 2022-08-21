package miu.edu.cs544.chioma.jobapp.messaging;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${spring.queueName}")
    private String queueName;

    public  void sendJob(Job job) {
        System.out.println("Send Job "+ job);
        MessageCreator messageCreator = session -> session.createObjectMessage(job);
        jmsTemplate.send(queueName,messageCreator);
    }
}
