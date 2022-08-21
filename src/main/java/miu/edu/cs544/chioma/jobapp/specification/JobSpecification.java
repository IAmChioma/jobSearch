package miu.edu.cs544.chioma.jobapp.specification;

import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobSpecification {
    public static Specification<Job> getJobsWithSalaryAndCompany(double salary, String state) {
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predSalary = criteriaBuilder.greaterThan(root.get("salary"),salary);
             //   Join<Job, Company> companyJoin = root.join("company");
                Predicate predState = criteriaBuilder.equal(root.get("company").get("address").get("state"),state);
                Join<Job, Application> applicationJoin = root.join("application");
                Predicate applicationResumePred = criteriaBuilder.le(applicationJoin.get("resume_version"),2.0);
                return criteriaBuilder.and(predSalary,predState,applicationResumePred);
            }
        };
    }
}
