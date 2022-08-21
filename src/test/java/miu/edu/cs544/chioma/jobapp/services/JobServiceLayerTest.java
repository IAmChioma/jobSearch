package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Address;
import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceLayerTest {
    @Mock
    private JobRepository jobRepository;

    @Autowired
    @InjectMocks()
    JobService jobService;
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
    @DisplayName("JUnit test for save job method")
    @Test
    public void saveJob_Success() {


        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job savedJob = jobRepository.save(job);
        assertEquals(savedJob.getTitle(), job.getTitle());
        assertEquals(savedJob.getSalary(), job.getSalary());
        assertEquals(savedJob.getApplication(), job.getApplication());

        verify (jobRepository,times(1)).save(any());
    }

    @DisplayName("JUnit test for get all jobs method")
    @Test
    public void getAllJob_Success() {
        jobRepository.save(job);

        when(jobRepository.findAll()).thenReturn(jobList);

        List<Job> jobList1 =jobService.findAll();
        assertEquals(jobList,jobList1);
        verify (jobRepository,times(1)).save(any());
        verify (jobRepository,times(1)).findAll();
    }

    @DisplayName("JUnit test to get a job given an id method")
    @Test
    public void getAJob_Success() {
        when(jobRepository.findById(job.getId())).thenReturn(job);
        assertEquals (jobService.findJobById(job.getId()), job);

    }
    @DisplayName("JUnit test to delete a job given an id method")
    @Test
    public void deleteAJob_Success() {

        jobRepository.deleteById(job.getId());
        verify(jobRepository,times(1)).deleteById(job.getId());

    }
}
