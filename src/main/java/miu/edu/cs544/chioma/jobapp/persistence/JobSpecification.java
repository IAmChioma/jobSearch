package miu.edu.cs544.chioma.jobapp.persistence;

import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobSpecification {
    public static Specification<Job> getJobsWithApplications() {
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Job, Application> applicationJoin = root.join("application");
                Predicate predicate = criteriaBuilder.isNotNull(applicationJoin.get("id"));
                return predicate;
            }
        };
    }
}
