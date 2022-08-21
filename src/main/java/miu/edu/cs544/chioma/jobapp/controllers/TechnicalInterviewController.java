package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.TechnicalInterview;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import miu.edu.cs544.chioma.jobapp.services.TechnicalInterviewService;
import miu.edu.cs544.chioma.jobapp.services.TechnicalInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TechnicalInterviewController {

    @Autowired
    private TechnicalInterviewService interviewService;

    @PostMapping(path = "/jobs/{id}/application/{applicationId}/interviews/technical")
    public Job createAndAddInterviewToApplicationInJob(@PathVariable int id, @PathVariable int applicationId, @RequestBody TechnicalInterview interview){
        return interviewService.createInterviewInJobApplication(id,applicationId,interview);
    }



    @DeleteMapping(path = "/jobs/{id}/application/{applicationId}/interviews/technical/{interviewId}")
    public String deleteTechnicalInterviewById(@PathVariable int id, @PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.deleteInterviewInApplication(id,applicationId,interviewId);

    }

    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/technical")
    public List<TechnicalInterview> getAllTechnicalInterviewInJobApplication(@PathParam ("hiring")String hiring,@PathVariable int id, @PathVariable int applicationId){
        return interviewService.getAllInterviewInApplication(id,applicationId);
    }
    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/technical/{interviewId}")
    public Interview getTechnicalInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.getInterviewInApplication(id,applicationId,interviewId);
    }

    @PutMapping(path = "/jobs/{id}/application/{applicationId}/interviews/technical/{interviewId}")
    public Interview updateTechnicalInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId, @RequestBody TechnicalInterview interview){
        return interviewService.updateInterviewApplication(id,applicationId,interviewId,interview);
    }




}
