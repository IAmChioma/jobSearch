package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.exceptions.JobNotFound;
import miu.edu.cs544.chioma.jobapp.repository.ApplicationRepository;
import miu.edu.cs544.chioma.jobapp.repository.CompanyRepository;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import miu.edu.cs544.chioma.jobapp.repository.SkillRepository;
import miu.edu.cs544.chioma.jobapp.specification.JobSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Value(value = "${deleted.successfully}")
    private String deletedResponse;

    public List<Job> findByCompanyState(String state){
       return  jobRepository.findByCompanyState(state);
    }
    public List<Job> findJobByCompanyWithinState(String state){
        return  jobRepository.findJobByCompanyWithinState(state);
    }
    public List<Job> findDistinctFirstByTitleIgnoreCase(String title){
        return  jobRepository.findDistinctFirstByTitleIgnoreCase(title);
    }
    public List<Job> findDistinctByTitleOrderBySalaryAsc(String title){
        return  jobRepository.findDistinctByTitleOrderBySalaryAsc(title);
    }

    public List<Job> getJobsWithApplicationNative(){
        return  jobRepository.getJobsWithApplicationNative();
    }

    public List<Job> getAllJobsWithSalaryAndStateIowa(double salary, String state){
        return jobRepository.findAll(JobSpecification.getJobsWithSalaryAndCompany(salary,state));
    }
    public Page<Job> getAllJobsByPage(int page,int count){
        Page<Job> jobs = jobRepository.findAll(PageRequest.of(page, count));
        return jobs;
    }
    public List<Job>  getAllJobs(){
        Sort sort = Sort.by("title").ascending()
                .and(Sort.by("salary").descending());
        List<Job> jobs = jobRepository.findAll(sort);
        return jobs;
    }
    public List<Job> findAll(){
        return  jobRepository.findAll();
    }

    public List<Job> getJobWithAtleast2Interviews(){
        return  jobRepository.getJobWithAtleast2Interviews();
    }



    private void deleteById(int id){
        jobRepository.deleteById(id);
    };

    public String deleteJob(int id) {
        Job foundJob = findById(id);
        if (!checkIfJobExist(foundJob)) {
            deleteById(id);
            return deletedResponse;
        }else {
            throw new JobNotFound("Job with id "+ id +" not found");

        }
    }

    private boolean checkIfJobExist(Job foundJob) {
        return Objects.isNull(foundJob);
    }

    private void delete(Job job){
          jobRepository.delete(job);
    }

    public String deleteJobByObject(Job job){
        delete(job);
        return deletedResponse;
    }
    public Job findById(int id){
        return  jobRepository.findById(id);
    }
    public Job findJobById(int id){
        Job foundJob= findById(id);
        if (!checkIfJobExist(foundJob)){
            return foundJob;
        }else {
            throw new JobNotFound("Job with id "+ id +" not found");

        }
    }


    public Job updateJob(int id, Job job){
        Job foundJob= findById(id);
        if (!checkIfJobExist(foundJob)){
            foundJob.setTitle(job.getTitle());
            foundJob.setSalary(job.getSalary());
            return save(foundJob);
        }else {
            throw new JobNotFound("Job with id "+ id +" not found");

        }
    }
    public Job save(Job job){
        return  jobRepository.save(job);
    }

}


