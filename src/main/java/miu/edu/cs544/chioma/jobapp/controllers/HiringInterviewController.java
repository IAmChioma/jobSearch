package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.HiringManagerInterview;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import miu.edu.cs544.chioma.jobapp.services.HiringManagerInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class HiringInterviewController {

    @Autowired
    private HiringManagerInterviewService interviewService;

    @PostMapping(path = "/jobs/{id}/application/{applicationId}/interviews/hiring")
    public Job createAndAddInterviewToApplicationInJob(@PathVariable int id, @PathVariable int applicationId, @RequestBody HiringManagerInterview interview){
        return interviewService.createInterviewInJobApplication(id,applicationId,interview);
    }



    @DeleteMapping(path = "/jobs/{id}/application/{applicationId}/interviews/hiring/{interviewId}")
    public String deleteHiringInterviewById(@PathVariable int id, @PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.deleteInterviewInApplication(id,applicationId,interviewId);

    }

    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/hiring")
    public List<HiringManagerInterview> getAllHiringInterviewInJobApplication(@PathParam ("hiring")String hiring,@PathVariable int id, @PathVariable int applicationId){
        return interviewService.getAllInterviewInApplication(id,applicationId);
    }
    @GetMapping(path = "/jobs/{id}/application/{applicationId}/interviews/hiring/{interviewId}")
    public Interview getHiringInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId){
        return interviewService.getInterviewInApplication(id,applicationId,interviewId);
    }

    @PutMapping(path = "/jobs/{id}/application/{applicationId}/interviews/hiring/{interviewId}")
    public Interview updateHiringInterviewInJobApplication(@PathVariable int id,@PathVariable int applicationId, @PathVariable int interviewId, @RequestBody HiringManagerInterview interview){
        return interviewService.updateInterviewApplication(id,applicationId,interviewId,interview);
    }




}
