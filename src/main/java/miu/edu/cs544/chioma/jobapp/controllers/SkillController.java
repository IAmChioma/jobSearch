package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.Skill;
import miu.edu.cs544.chioma.jobapp.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;


    @PostMapping(path = "/jobs/{id}/skills")
    public Job createAndAddSkillToJob(@PathVariable int id, @RequestBody Skill skill){
        return skillService.createSkillInJob(id,skill);

    }



    @DeleteMapping(path = "/jobs/{id}/skills/{skillId}")
    public String deleteSkillById(@PathVariable int id, @PathVariable int skillId){
        return skillService.deleteSkillInJob(id,skillId);

    }

    @GetMapping(path = "/jobs/{id}/skills")
    public List<Skill> getAllSkillsInJob(@PathVariable int id){
        return skillService.getJobSkills(id);
    }
    @GetMapping(path = "/jobs/{id}/skills/{skillId}")
    public Skill getSkillInJob(@PathVariable int id,@PathVariable int skillId){
        return skillService.getASkillInJob(id,skillId);
    }

    @PutMapping(path = "/jobs/{id}/skills/{skillId}")
    public Skill updateSkillInJob(@PathVariable int id,@PathVariable int skillId, @RequestBody Skill skill){
        return skillService.updateSkillInJob(id,skillId,skill);
    }




}
