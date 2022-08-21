package miu.edu.cs544.chioma.jobapp.messaging;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JobReceiver {
    @Autowired
    private JobService jobService;


    @JmsListener(destination = "${spring.queueName}")
    public void receiveJob(Job job){
        Job updatedJob=  jobService.save(job);
        System.out.println("Received Job and saved - " + updatedJob);
    }



}
