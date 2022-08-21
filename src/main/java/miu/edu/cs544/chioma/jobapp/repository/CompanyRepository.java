package miu.edu.cs544.chioma.jobapp.repository;

import miu.edu.cs544.chioma.jobapp.entities.Address;
import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    public List<Company> findAll();
    public Company findById(int id);
   // @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    public Company save(Company company);
    public Company deleteById(int id);


}
