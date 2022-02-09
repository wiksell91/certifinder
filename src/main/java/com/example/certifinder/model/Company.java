package com.example.certifinder.model;




import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table
@Entity
public class  Company {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    private Long id;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Orderreq> orderreqs = new ArrayList<>();


    private String companyname;
    private String orgnumber;
    private String city;
    private Role role;

    public Company(String companyname, String orgnumber, String city, Role role) {
        this.companyname = companyname;
        this.orgnumber = orgnumber;
        this.city = city;
        this.role = role;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getOrgnumber() {
        return orgnumber;
    }

    public void setOrgnumber(String orgnumber) {
        this.orgnumber = orgnumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setOrderreqs(List<Orderreq> orderreqs) {
        this.orderreqs = orderreqs;
    }
}
