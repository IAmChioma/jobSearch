package miu.edu.cs544.chioma.jobapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TBL_skill")
public class Skill implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String experience;
    private String description;
    private String language;



    public Skill(String name, String experience, String description, String language) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.description = description;
        this.language = language;
    }

    public Skill() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
    @PostRemove()
    public void log(){
        System.out.println("Skill deleted "+ this.id);
    }
}
