package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.exceptions.ApplicationNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.CompanyNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.JobNotFound;
import miu.edu.cs544.chioma.jobapp.repository.ApplicationRepository;
import miu.edu.cs544.chioma.jobapp.repository.CompanyRepository;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private JobRepository jobRepository;

    @Value(value = "${deleted.successfully}")
    private String deletedResponse;
    @Value(value = "${not.found}")
    private String notFoundResponse;
    public Job createApplicationInJob(int id, Application application){
        Job foundJob = jobRepository.findById(id);
        Application createdApplication = applicationRepository.save(application);
        foundJob.setApplication(createdApplication);
        return jobRepository.save(foundJob);
    }

    public String deleteApplicationInJob(int id,int applicationId){
        Job foundJob = jobRepository.findById(id);
        Application applicationFound= applicationRepository.findById(applicationId);
        String message = notFoundResponse;
        if (Objects.isNull(foundJob)) {
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(applicationFound)) {
            throw new ApplicationNotFound("Application with id "+ applicationId +" not found");
        }

        foundJob.setApplication(null);
        applicationRepository.deleteById(applicationId);
        message = deletedResponse;
        jobRepository.save(foundJob);

        return message;
    }

    public Application getJobApplication(int id,int applicationId){
        Job foundJob= jobRepository.findById(id);
        Application foundApplication = applicationRepository.findById(applicationId);
        checkForNullAndThrowException(id, applicationId, foundJob, foundApplication);
        return foundApplication;

    }
    public Application updateApplicationInJob(int id,int applicationId, Application application){
        Job foundJob= jobRepository.findById(id);
        Application applicationFound= applicationRepository.findById(applicationId);
        checkForNullAndThrowException(id, applicationId, foundJob, applicationFound);

        applicationFound.setDate(application.getDate());
        applicationFound.setResume_version(application.getResume_version());
        foundJob.setApplication(applicationFound);
        jobRepository.save(foundJob);
        return applicationFound;

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


}
