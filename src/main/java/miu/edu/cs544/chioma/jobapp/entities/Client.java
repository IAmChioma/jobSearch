package miu.edu.cs544.chioma.jobapp.entities;

import javax.persistence.*;

@DiscriminatorValue("Cli")
@Entity
public class Client extends Company {
    private String mission;
    private String reason;
    private String website;


    public Client() {
    }
    public Client(String name, Address address, String mission, String reason, String website) {
        super(name, address);
        this.mission = mission;
        this.reason = reason;
        this.website = website;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


}
