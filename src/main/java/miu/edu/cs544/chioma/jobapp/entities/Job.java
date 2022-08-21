package miu.edu.cs544.chioma.jobapp.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_Job")
@NamedQueries({
        @NamedQuery(name = "Job.findByCompanyState", query = "select j from Job j where j.company.address.state=:state"),
        @NamedQuery(name = "Job.findJobByCompanyWithinState", query = "select j from Job j where  j.company.address.state =:state")
})

public class Job  implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private double salary;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Application.class)
    private Application application;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();

    @Version
    private int version;
    public Job() {
    }


    public Job(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }
    public Job(String title, double salary, Application app) {
        this.title = title;
        this.salary = salary;
        this.application = app;
    }

    public Job(String title, double salary, Company company, Application application) {
        this.title = title;
        this.salary = salary;
        this.company = company;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", company='" + company + '\'' +
          //      ", application_resume_version='" + application.getResume_version() + '\'' +
           //     ", application_date=" + application.getDate() +
                '}';
    }

    @PostRemove()
    public void log(){
        System.out.println("Job deleted "+ this.id);
    }

}
