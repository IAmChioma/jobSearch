package miu.edu.cs544.chioma.jobapp.entities.interview;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Tec")
public class TechnicalInterview extends Interview {
    private int duration;
    @Enumerated(EnumType.ORDINAL)
    private Location location;

    private String questions;

    public TechnicalInterview() {
    }

    public TechnicalInterview(LocalDate date, String phone, String email, int duration, Location location) {
        super(date, phone, email);
        this.duration = duration;
        this.location = location;
    }

    public TechnicalInterview(LocalDate date, String phone, String email, int duration, Location location,String questions) {
        super(date, phone, email);
        this.duration = duration;
        this.location = location;
        this.questions = questions;
    }

    public int getDuration() {
        return duration;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
