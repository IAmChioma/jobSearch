package miu.edu.cs544.chioma.jobapp.services;

import miu.edu.cs544.chioma.jobapp.entities.Address;
import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.repository.ApplicationRepository;
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
public class ApplicationServiceLayerTest {
    @Mock
    private JobRepository jobRepository;
    @Mock
    private ApplicationRepository applicationRepository;

    @Autowired
    @InjectMocks()
    ApplicationService applicationService;
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
    @DisplayName("JUnit test for save application method")
    @Test
    public void saveApplication_Success() {
        when(applicationRepository.save(any(Application.class))).thenReturn(application1);

        Application savedApplication = applicationRepository.save(application1);
        assertEquals(savedApplication.getDate(), application1.getDate());
        assertEquals(savedApplication.getResume_version(), application1.getResume_version());

        verify (applicationRepository,times(1)).save(any());
    }


    @DisplayName("JUnit test to get an application in a job given an id method")
    @Test
    public void getAnApplication_Success() {
        when(jobRepository.save(any(Job.class))).thenReturn(job);
        when(applicationRepository.save(any(Application.class))).thenReturn(application1);
        Job savedJob = jobRepository.save(job);
        Application savedApplication = applicationRepository.save(application1);

        savedJob.setApplication(savedApplication);

        lenient().when(jobRepository.findById(job.getId())).thenReturn(savedJob);
        lenient().when(applicationRepository.findById(application1.getId())).thenReturn(savedApplication);
        assertEquals(savedJob.getApplication(), savedApplication);
        assertEquals (applicationService.getJobApplication(savedJob.getId(), savedApplication.getId()), application1);


    }
    @DisplayName("JUnit test to delete an application given an id method")
    @Test
    public void deleteApplication_Success() {
        when(jobRepository.save(any(Job.class))).thenReturn(job);
        Job savedJob = jobRepository.save(job);

        savedJob.setApplication(null);
        applicationRepository.deleteById(application1.getId());

        assertNull(savedJob.getApplication());

        verify(applicationRepository,times(1)).deleteById(application1.getId());

    }
}
