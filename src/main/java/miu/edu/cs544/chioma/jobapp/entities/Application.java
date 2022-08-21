package miu.edu.cs544.chioma.jobapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import miu.edu.cs544.chioma.jobapp.entities.interview.Interview;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TBL_Application")
public class Application implements Serializable {
    @Id @GeneratedValue
    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private double resume_version;

    @OneToMany()
    private List<Interview> interviews = new ArrayList<>();
    @Version
    private int version;

    public Application() {
    }


    public Application(LocalDate date, double resume_version) {
        this.date = date;
        this.resume_version = resume_version;
    }


    public Application(LocalDate date, double resume_version, List<Interview> interviews) {
        this.date = date;
        this.resume_version = resume_version;
        this.interviews = interviews;
    }

    public int getId() {
        return id;
    }
    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public int getVersion() {
        return version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getResume_version() {
        return resume_version;
    }

    public void setResume_version(double resume_version) {
        this.resume_version = resume_version;
    }


    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                ", resume_version=" + resume_version +
                '}';
    }
    @PostRemove()
    public void log(){
        System.out.println("Application deleted "+ this.id);
    }
}
