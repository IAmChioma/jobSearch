package miu.edu.cs544.chioma.jobapp.entities.interview;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Hir")
public class HiringManagerInterview extends Interview {
    private int team_size;
    private LocalDate start_date;

    public HiringManagerInterview() {
    }

    public HiringManagerInterview(LocalDate date, String phone, String email, int team_size, LocalDate start_date) {
        super(date, phone, email);
        this.team_size = team_size;
        this.start_date = start_date;
    }

    public int getTeam_size() {
        return team_size;
    }

    public void setTeam_size(int team_size) {
        this.team_size = team_size;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
}
