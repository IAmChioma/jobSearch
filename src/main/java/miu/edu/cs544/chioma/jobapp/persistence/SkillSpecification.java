package miu.edu.cs544.chioma.jobapp.persistence;

import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.entities.Skill;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SkillSpecification {
    public static Specification<Skill> getAllSkillsWithSalaryGreater(double salary) {
        return new Specification<Skill>() {
            @Override
            public Predicate toPredicate(Root<Skill> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Skill, Job> jobJoin = root.join("job");
                return  criteriaBuilder.greaterThan(jobJoin.get("salary"), salary);
            }
        };
    }

    public static Specification<Skill> getAllSkillsWithSalaryGreaterAndCompanyInState(double salary, String state) {
        return new Specification<Skill>() {
            @Override
            public Predicate toPredicate(Root<Skill> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Skill, Job> jobJoin = root.join("job");
                Predicate salaryPredicate = criteriaBuilder.greaterThan(jobJoin.get("salary"), salary);
                Predicate companyPredicate = criteriaBuilder.greaterThan(jobJoin.get("company").get("address").get("state"), state);

                return criteriaBuilder.and(salaryPredicate,companyPredicate);
            }
        };
    }
}
