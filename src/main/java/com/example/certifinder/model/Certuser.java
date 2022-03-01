package com.example.certifinder.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Table
public class Certuser {

    @Id
    @SequenceGenerator(
            name = "certuser_sequence",
            sequenceName = "certuser_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "certuser_sequence"
    )
    private Long id;
   // @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    //@Column(nullable = false)
    private String password;
    //@Column(nullable = false)
    private String name;
    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private Role role;
    //@Column(nullable = false)
    private Integer age;
    //@Column(nullable = false)
    private String city;


    @OneToMany(mappedBy = "certuser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Certstatus> certstatuses = new ArrayList<>();


    @OneToMany(mappedBy = "certuser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Orderreq> orderreqs = new ArrayList<>();


    public Certuser(String email, String password, String username, String name, Role role, Integer age, String city, List<Certstatus> certstatuses, List<Orderreq> orderreqs) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.role = role;
        this.age = age;
        this.city = city;
        this.certstatuses = certstatuses;
        this.orderreqs = orderreqs;
    }

    public Certuser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public void setCertstatuses(List<Certstatus> certstatuses) {
        this.certstatuses = certstatuses;
    }

    public void setOrderreqs(List<Orderreq> orderreqs) {
        this.orderreqs = orderreqs;
    }
}
