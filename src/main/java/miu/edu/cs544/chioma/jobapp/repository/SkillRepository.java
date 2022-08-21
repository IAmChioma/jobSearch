package miu.edu.cs544.chioma.jobapp.repository;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.Skill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer>{

    public Skill findById(int id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Skill save(Skill skill);
    public Skill deleteById(int id);
    @Modifying
    @Transactional
    @Query(value = "update Skill c set c =?1  where c.id = ?2")
    public Skill update(Skill skill, int id);
}
