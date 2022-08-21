package miu.edu.cs544.chioma.jobapp.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Interview>, JpaSpecificationExecutor<Job> {
    public List<Job> findByCompanyState(String state);
    public List<Job> findJobByCompanyWithinState(String state);
    public List<Job> findDistinctFirstByTitleIgnoreCase(String title);
    public List<Job> findDistinctByTitleOrderBySalaryAsc(String title);



    @Query(value = "select j.* from TBL_job j join TBL_Application a on a.id= j.application_id", nativeQuery = true)
    @JsonIgnore
    public List<Job> getJobsWithApplicationNative();

    @Query(value = "select j from Job j")
    public List<Job> findAll();


    @Query(value = "select j from Job  j join Application a on a.id = j.application.id where a.interviews.size>2")
    public List<Job> getJobWithAtleast2Interviews();


    @Transactional
    public void deleteById(int id);

    public void delete(Job job);

    public Job findById(int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Job save(Job job);





}
