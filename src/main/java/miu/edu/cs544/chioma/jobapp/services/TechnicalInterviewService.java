package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.TechnicalInterview;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import miu.edu.cs544.chioma.jobapp.exceptions.ApplicationNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.InterviewNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.JobNotFound;
import miu.edu.cs544.chioma.jobapp.repository.ApplicationRepository;
import miu.edu.cs544.chioma.jobapp.repository.TechnicalInterviewRepository;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TechnicalInterviewService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TechnicalInterviewRepository technicalInterviewRepository;

    @Value(value = "${deleted.successfully}")
    private String deletedResponse;
    @Value(value = "${not.found}")
    private String notFoundResponse;

    public Job createInterviewInJobApplication(int id, int applicationId, TechnicalInterview interview){
        Job foundJob = jobRepository.findById(id);
        Application applicationFound= applicationRepository.findById(applicationId);
        TechnicalInterview createdInterview = technicalInterviewRepository.save(interview);
        checkForNullAndThrowException(id, applicationId, foundJob, applicationFound);
        foundJob.getApplication().getInterviews().add(createdInterview);
        return jobRepository.save(foundJob);
    }

    public String deleteInterviewInApplication(int id,int applicationId, int interviewId){
        Job foundJob = jobRepository.findById(id);
        Application applicationFound= applicationRepository.findById(applicationId);
        TechnicalInterview interviewFound= technicalInterviewRepository.findById(interviewId);
        String message = notFoundResponse;
        checkForNullApplicationJobInterviewAndThrowException(id, applicationId, interviewId, foundJob, applicationFound, interviewFound);

        foundJob.getApplication().getInterviews().remove(interviewFound);
        technicalInterviewRepository.deleteById(interviewId);
        message = deletedResponse;
        jobRepository.save(foundJob);
        return message;
    }

    public Interview getInterviewInApplication(int id, int applicationId, int interviewId){
        Job foundJob= jobRepository.findById(id);
        Application foundApplication = applicationRepository.findById(applicationId);
        TechnicalInterview foundInterview= technicalInterviewRepository.findById(interviewId);
        checkForNullApplicationJobInterviewAndThrowException(id, applicationId, interviewId, foundJob, foundApplication, foundInterview);
        checkIfApplicationIdIsInJobAndInterviewIsinApplication(applicationId, interviewId, foundJob, foundApplication, foundInterview);
        return foundInterview;

    }


    public List<TechnicalInterview> getAllInterviewInApplication(int id, int applicationId){
        Job foundJob= jobRepository.findById(id);
        Application foundApplication = applicationRepository.findById(applicationId);
        if (Objects.isNull(foundJob)) {
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(foundApplication)) {
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found");
        }
        if(foundJob.getApplication().getId() != applicationId){
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found in job");
        }
        return technicalInterviewRepository.findAllTechnicalInterviewInJobApplication(id,applicationId);

    }
    public Interview updateInterviewApplication(int id,int applicationId,int interviewId, TechnicalInterview interview){
        Job foundJob= jobRepository.findById(id);
        Application foundApplication= applicationRepository.findById(applicationId);
        TechnicalInterview foundInterview= technicalInterviewRepository.findById(interviewId);
        checkForNullApplicationJobInterviewAndThrowException(id, applicationId, interviewId, foundJob, foundApplication, foundInterview);

        checkIfApplicationIdIsInJobAndInterviewIsinApplication(applicationId, interviewId, foundJob, foundApplication, foundInterview);
        foundInterview.setDate(interview.getDate());
        foundInterview.setEmail(interview.getEmail());
        foundInterview.setPhone(interview.getPhone());
        foundInterview.setDuration(interview.getDuration());
        foundInterview.setLocation(interview.getLocation());
        foundInterview.setQuestions(interview.getQuestions());

        return technicalInterviewRepository.save(foundInterview);

    }

    private void checkIfApplicationIdIsInJobAndInterviewIsinApplication(int applicationId, int interviewId, Job foundJob, Application foundApplication, Interview foundInterview) {
        if(foundJob.getApplication().getId() != applicationId){
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found in job");
        }
        if (!foundApplication.getInterviews().contains(foundInterview)) {
            throw new InterviewNotFound("Interview with id " + interviewId + " not found in Application");
        }
    }

    private void checkForNullAndThrowException(int id, int applicationId, Job foundJob, Application applicationFound) {
        if (Objects.isNull(foundJob)){
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(applicationFound) ){
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found");
        }
        if (foundJob.getApplication().getId() != applicationId){
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found in Job");
        }
    }
    private void checkForNullApplicationJobInterviewAndThrowException(int id, int applicationId, int interviewId, Job foundJob, Application foundApplication, Interview foundInterview) {
        if (Objects.isNull(foundJob)) {
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(foundApplication)) {
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found");
        }
        if (Objects.isNull(foundInterview)) {
            throw new InterviewNotFound("Interview with id "+ interviewId +" not found");
        }
    }


}
