package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Address;
import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.repository.CompanyRepository;
import miu.edu.cs544.chioma.jobapp.repository.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceLayerTest {
    @Mock
    private JobRepository jobRepository;
    @Mock
    private CompanyRepository companyRepository;

    @Autowired
    @InjectMocks()
    CompanyService companyService;
    Address address1;
    Company company1;
    Application application1;
    Job job;
    List<Job> jobList ;

    @BeforeEach
    void initUseCase() {
        address1 = new Address("4th Street", "Fairfield", "IA",52557);
        company1 = new Company("PwC",address1);
        application1 = new Application(LocalDate.of(2022,8,11),1.0);
        job = new Job("Software developer",270000,company1,application1);
        jobList = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        address1=null;
        company1 =null;
        application1 = null;
        job  = null;
        jobList = null;
    }
    @DisplayName("JUnit test for save company method")
    @Test
    public void saveCompany_Success() {
        when(companyRepository.save(any(Company.class))).thenReturn(company1);

        Company savedCompany = companyRepository.save(company1);
        assertEquals(savedCompany.getName(), company1.getName());
        assertEquals(savedCompany.getVersion(), company1.getVersion());

        verify (companyRepository,times(1)).save(any());
    }


    @DisplayName("JUnit test to get a company in a job given an id method")
    @Test
    public void getACompany_Success() {
        when(jobRepository.save(any(Job.class))).thenReturn(job);
        when(companyRepository.save(any(Company.class))).thenReturn(company1);
        Job savedJob = jobRepository.save(job);
        Company savedCompany = companyRepository.save(company1);

        savedJob.setCompany(savedCompany);

        lenient().when(jobRepository.findById(job.getId())).thenReturn(savedJob);
        lenient().when(companyRepository.findById(company1.getId())).thenReturn(savedCompany);
        assertEquals(savedJob.getCompany(), savedCompany);
        assertEquals (companyService.getJobCompany(savedJob.getId(), savedCompany.getId()), company1);



    }
    @DisplayName("JUnit test to delete a company given an id method")
    @Test
    public void deleteACompany_Success() {
        when(jobRepository.save(any(Job.class))).thenReturn(job);
        Job savedJob = jobRepository.save(job);

        savedJob.setCompany(null);
        companyRepository.deleteById(company1.getId());

        assertNull(savedJob.getCompany());

        verify(companyRepository,times(1)).deleteById(company1.getId());


    }
}
