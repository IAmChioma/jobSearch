package miu.edu.cs544.chioma.jobapp.entities.interview;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import miu.edu.cs544.chioma.jobapp.entities.Application;
import miu.edu.cs544.chioma.jobapp.entities.Job;

import javax.persistence.*;
import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "I_TYPE")
@Entity
public abstract class Interview {
    @Id
    @GeneratedValue
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String phone;
    private String email;

    public Interview() {
    }

    public Interview(LocalDate date, String phone, String email) {
        this.date = date;
        this.phone = phone;
        this.email = email;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    @PostRemove()
    public void log(){
        System.out.println("Interview deleted "+ this.id);
    }
}
