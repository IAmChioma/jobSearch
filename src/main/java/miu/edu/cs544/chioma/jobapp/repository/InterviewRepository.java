package miu.edu.cs544.chioma.jobapp.repository;

import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview,Integer> {
    public List<Interview> findAll();
    public Interview findById(int id);
    public Interview save(Interview interview);
    public Interview deleteById(int id);
}
