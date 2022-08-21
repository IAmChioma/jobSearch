package miu.edu.cs544.chioma.jobapp.entities;


import javax.persistence.*;
import java.io.Serializable;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="C_TYPE")
@Entity public class Company  implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String name;
    @Embedded
    private Address address;

    @Version
    private int version;

    public Company() {
    }

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
    @PostRemove()
    public void log(){
        System.out.println("Company deleted "+ this.id);
    }
}
