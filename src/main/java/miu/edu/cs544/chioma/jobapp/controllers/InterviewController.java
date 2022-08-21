package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import miu.edu.cs544.chioma.jobapp.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterviewController {

    @Autowired
    private InterviewService interviewService;



    @DeleteMapping(path = "/jobs/{id}/application/{applicationId}/interviews/{interviewId}")
    public String deleteInterviewById(@PathVariable int id, @PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.deleteInterviewInApplication(id,applicationId,interviewId);

    }

    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews")
    public List<Interview> getAllInterviewInJobApplication(@PathVariable int id, @PathVariable int applicationId){
        return interviewService.getAllInterviewInApplication(id,applicationId);
    }
    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/{interviewId}")
    public Interview getInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.getInterviewInApplication(id,applicationId,interviewId);
    }





}
