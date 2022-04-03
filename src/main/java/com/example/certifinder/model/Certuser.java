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


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table
public class Certuser implements UserDetails {

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

    @Column(nullable = false)
    private Role role;
    //@Column(nullable = false)
    private String city;
    private Boolean locked = false;
    private Boolean enabled = false;


    @OneToMany(mappedBy = "certuser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Certstatus> certstatuses = new ArrayList<>();


    @OneToMany(mappedBy = "certuser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Orderreq> orderreqs = new ArrayList<>();


    public Certuser(String email, String password, String fullName, Role role, String city) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.city = city;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
