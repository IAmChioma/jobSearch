package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.Skill;
import miu.edu.cs544.chioma.jobapp.exceptions.JobNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.SkillNotFound;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import miu.edu.cs544.chioma.jobapp.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;

    @Value(value = "${deleted.successfully}")
    private String deletedResponse;
    @Value(value = "${not.found}")
    private String notFoundResponse;
    public Job createSkillInJob(int id, Skill skill){
        Job foundJob = jobRepository.findById(id);
        Skill createdSkill = skillRepository.save(skill);
        foundJob.getSkills().add(createdSkill);
        return  jobRepository.save(foundJob);

    }

    public String deleteSkillInJob(int id,int skillId){
        Job foundJob = jobRepository.findById(id);
        Skill skillFound= skillRepository.findById(skillId);
        String message = notFoundResponse;
        if (Objects.isNull(foundJob)) {
            throw new JobNotFound("Job with ID with id "+ id +" not found");
        }
        if (Objects.isNull(skillFound)) {
            throw new SkillNotFound("Skill  with id "+ skillId +" not found");
        }

        foundJob.getSkills().remove(skillFound);
        skillRepository.deleteById(skillId);
        message = deletedResponse;

        return message;
    }

    public List<Skill> getJobSkills(int id){
        Job foundJob= jobRepository.findById(id);
        if (Objects.isNull(foundJob)){
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        return foundJob.getSkills();

    }
    public Skill getASkillInJob(int id, int skillId){
        Job foundJob= jobRepository.findById(id);
        Skill foundSkill = skillRepository.findById(skillId);
        checkForNullAndThrowException(id, skillId, foundJob, foundSkill);


        return foundSkill;
    }
    public Skill updateSkillInJob(int id,int skillId, Skill skill){

        Job foundJob= jobRepository.findById(id);
        Skill foundSkill= skillRepository.findById(skillId);
        checkForNullAndThrowException(id, skillId, foundJob, foundSkill);

        foundSkill.setName(skill.getName());
        foundSkill.setLanguage(skill.getLanguage());
        foundSkill.setDescription(skill.getDescription());
        foundSkill.setExperience(skill.getExperience());
        return  skillRepository.save(foundSkill);


    }

    private void checkForNullAndThrowException(int id, int skillId, Job foundJob, Skill foundSkill) {
        if (Objects.isNull(foundJob)){
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(foundSkill) ){
            throw new SkillNotFound("Skill with id "+ skillId +" not found");
        }
        if (!foundJob.getSkills().contains(foundSkill)){
            throw new SkillNotFound("Skill with id "+ skillId +" not found in Job");
        }
    }

}
