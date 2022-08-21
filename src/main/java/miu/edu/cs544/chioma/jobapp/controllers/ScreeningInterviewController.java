package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import miu.edu.cs544.chioma.jobapp.entities.interview.ScreeningInterview;
import miu.edu.cs544.chioma.jobapp.entities.interview.TechnicalInterview;
import miu.edu.cs544.chioma.jobapp.services.ScreeningInterviewService;
import miu.edu.cs544.chioma.jobapp.services.TechnicalInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ScreeningInterviewController {

    @Autowired
    private ScreeningInterviewService interviewService;

    @PostMapping(path = "/jobs/{id}/application/{applicationId}/interviews/screening")
    public Job createAndAddInterviewToApplicationInJob(@PathVariable int id, @PathVariable int applicationId, @RequestBody ScreeningInterview interview){
        return interviewService.createInterviewInJobApplication(id,applicationId,interview);
    }



    @DeleteMapping(path = "/jobs/{id}/application/{applicationId}/interviews/screening/{interviewId}")
    public String deleteScreeningInterviewById(@PathVariable int id, @PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.deleteInterviewInApplication(id,applicationId,interviewId);

    }

    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/screening")
    public List<ScreeningInterview> getAllScreeningInterviewInJobApplication(@PathParam ("hiring")String hiring,@PathVariable int id, @PathVariable int applicationId){
        return interviewService.getAllInterviewInApplication(id,applicationId);
    }
    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/screening/{interviewId}")
    public Interview getScreeningInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.getInterviewInApplication(id,applicationId,interviewId);
    }

    @PutMapping(path = "/jobs/{id}/application/{applicationId}/interviews/screening/{interviewId}")
    public Interview updateScreeningInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId, @RequestBody ScreeningInterview interview){
        return interviewService.updateInterviewApplication(id,applicationId,interviewId,interview);
    }




}
