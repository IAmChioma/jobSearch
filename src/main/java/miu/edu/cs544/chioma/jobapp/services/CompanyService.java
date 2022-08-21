package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.exceptions.CompanyNotFound;
import miu.edu.cs544.chioma.jobapp.exceptions.JobNotFound;
import miu.edu.cs544.chioma.jobapp.repository.CompanyRepository;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Value(value = "${deleted.successfully}")
    private String deletedResponse;
    @Value(value = "${not.found}")
    private String notFoundResponse;

    public Job createCompanyInJob(int id, Company company){
        Job foundJob = jobRepository.findById(id);
        Company createdCompany = companyRepository.save(company);
        foundJob.setCompany(createdCompany);
        return jobRepository.save(foundJob);
    }

    public String deleteCompanyInJob(int id,int companyId){
        Job foundJob = jobRepository.findById(id);
        Company companyFound= companyRepository.findById(companyId);
        String message = notFoundResponse;
        if (Objects.isNull(foundJob)) {
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(companyFound)) {
            throw new CompanyNotFound("Company with id "+ companyId +" not found");
        }

        foundJob.setCompany(null);
        companyRepository.deleteById(companyId);
        message = deletedResponse;
        jobRepository.save(foundJob);

        return message;
    }

    public Company getJobCompany(int id,int companyId){
        Job foundJob= jobRepository.findById(id);
        Company companyFound = companyRepository.findById(companyId);
        checkForNullAndThrowException(id, companyId, foundJob, companyFound);
        return companyFound;

    }
    public Company updateCompanyInJob(int id,int companyId, Company company){
        Job foundJob= jobRepository.findById(id);
        Company companyFound= companyRepository.findById(companyId);
        checkForNullAndThrowException(id, companyId, foundJob, companyFound);

        companyFound.setName(company.getName());
        companyFound.setAddress(company.getAddress());
        foundJob.setCompany(companyFound);
        jobRepository.save(foundJob);
        return companyFound;

    }

    private void checkForNullAndThrowException(int id, int companyId, Job foundJob, Company companyFound) {
        if (Objects.isNull(foundJob)){
            throw new JobNotFound("Job with id "+ id +" not found");
        }
        if (Objects.isNull(companyFound) ){
            throw new CompanyNotFound("Company with id "+ companyId +" not found");
        }
        if (foundJob.getCompany().getId() != companyId){
            throw new CompanyNotFound("Company with id "+ companyId +" not found in Job");
        }
    }


}
