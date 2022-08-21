package miu.edu.cs544.chioma.jobapp.repository;

import miu.edu.cs544.chioma.jobapp.entities.interview.HiringManagerInterview;
import miu.edu.cs544.chioma.jobapp.entities.interview.ScreeningInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface HiringManagerInterviewRepository extends JpaRepository<HiringManagerInterview,Integer> {
    public List<HiringManagerInterview> findAll();
    public HiringManagerInterview findById(int id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public HiringManagerInterview save(HiringManagerInterview interview);
    public HiringManagerInterview deleteById(int id);
    @Query(value = "select i.*,hmi.* from tbl_job j join tbl_application ta on ta.id = j.application_id\n" +
            "    join tbl_application_interviews tai on j.application_id = tai.application_id\n" +
            "    join interview i on i.id = tai.interviews_id   join hiring_manager_interview hmi on i.id = hmi.id\n" +
            "           where  j.id = ?1 and ta.id=?2 and i.i_type='Hir'", nativeQuery = true)
    @Lock(LockModeType.PESSIMISTIC_READ)
    public List<HiringManagerInterview> findAllHiringInterviewInJobApplication(int id, int applicationId);

}
