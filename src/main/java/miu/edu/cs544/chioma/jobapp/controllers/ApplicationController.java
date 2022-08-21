package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;



    @PostMapping(path = "/jobs/{id}/application")
    public Job createApplicationForJob(@PathVariable int id, @RequestBody Application application){
        return  applicationService.createApplicationInJob(id,application);

    }

    @DeleteMapping(path = "/jobs/{id}/application/{applicationId}")
    public String deleteApplicationById(@PathVariable int id, @PathVariable int applicationId){
        return applicationService.deleteApplicationInJob(id,applicationId);

    }


    @GetMapping(path = "/jobs/{id}/application/{applicationId}")
    public Application getApplicationInJob(@PathVariable int id,@PathVariable int applicationId){
        return applicationService.getJobApplication(id,applicationId);

    }

    @PutMapping(path = "/jobs/{id}/application/{applicationId}")
    public Application updateApplicationInJob(@PathVariable int id,@PathVariable int applicationId, @RequestBody Application application){
        return applicationService.updateApplicationInJob(id, applicationId, application);
    }



}
