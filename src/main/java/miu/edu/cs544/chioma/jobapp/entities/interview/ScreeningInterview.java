package miu.edu.cs544.chioma.jobapp.entities.interview;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Scr")
public class ScreeningInterview extends Interview {
    private String name;

    private String result;

    public ScreeningInterview() {
    }
    public ScreeningInterview(LocalDate date, String phone, String email, String name, String result) {
        super(date, phone, email);
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
