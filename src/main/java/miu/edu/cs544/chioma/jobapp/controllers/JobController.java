package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.messaging.Sender;
import miu.edu.cs544.chioma.jobapp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private Sender sender;


    @Value(value = "${created.successfully}")
    private String createdResponse;


    @GetMapping(path = "/jobs")
    public List<Job> getAllJobs() {
        return jobService.findAll();
    }

    @GetMapping(path = "/pagedJobs")
    public Page<Job> getAllJobsByPage(@PathParam("page") int page, @PathParam("count") int count) {
        Page<Job> jobs = jobService.getAllJobsByPage(page, count);
        return jobs;
    }

    @GetMapping(path = "/jobsWithApplication")
    public List<Job> getAllJobsWithApplication() {
        return jobService.getJobsWithApplicationNative();
    }

    @GetMapping(path = "/jobsWithSalary")
    public List<Job> getAllJobsWithSalaryAndStateIowa(@PathParam("salary") double salary, @PathParam("state") String state) {
        return jobService.getAllJobsWithSalaryAndStateIowa(salary, state);
    }

//    @PostMapping(path = "/jobs")
//    public Job job(@RequestBody Job job) {
//        return jobService.save(job);
//    }

    @DeleteMapping(path = "/jobs/{id}")
    public String deleteJobById(@PathVariable int id) {
        return jobService.deleteJob(id);

    }


    @DeleteMapping(path = "/jobsByObject")
    public String deleteJob(@RequestBody Job job) {
        return jobService.deleteJobByObject(job);
    }

    @GetMapping(path = "/jobs/{id}")
    public Job getJob(@PathVariable int id) {
        return jobService.findJobById(id);

    }

    @PutMapping(path = "/jobs/{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @PostMapping(path = "/jobs")
    public String createJobViaQueue(@RequestBody Job job) {
        sender.sendJob(job);
        return createdResponse;
    }

}
