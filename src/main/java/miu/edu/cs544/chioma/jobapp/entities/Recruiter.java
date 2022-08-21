package miu.edu.cs544.chioma.jobapp.entities;

import javax.persistence.*;
import java.util.List;

@DiscriminatorValue("Rec")
@Entity
public class Recruiter extends Company{
    @OneToMany
    private List<Client> clients;
    public Recruiter(String name, Address address) {
        super(name, address);
    }

    public Recruiter() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
