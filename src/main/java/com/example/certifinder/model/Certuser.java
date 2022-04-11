package com.example.certifinder.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table
public class Certuser  {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    //@Column(nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

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

    public Certuser(Long id, String email, String password, String fullName, Collection<Role> roles, String city) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
        this.city = city;
    }
}






