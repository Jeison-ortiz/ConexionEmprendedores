package com.conexemi.emi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 150)
    private String mobile;

    @Column(nullable = false, length = 150)
    private String userPassword;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "idCity")
    @JsonBackReference
    private City idCity;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"idUser", "idRole"})
    )
    private List<Role> roles;

    @OneToMany(targetEntity = Entrepreneurship.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Entrepreneurship> entrepreneurships;

    @OneToMany(targetEntity = Comments.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Reaction> reactions;



    // Returns a list of the authorities granted to the user.
    // In this case, it is based on the user's role and makes them an authority with SimpleGrantedAuthority.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole().name()))
                .toList();
    }


    // Returns the user's password.
    // The 'userPassword' variable is used here, which should contain the user's stored password.
    @Override
    public String getPassword() {
        return userPassword;
    }


    // Returns the username of the user.
    // In this case, the user's email address ('email') is used as the unique identifier.
    @Override
    public String getUsername() {
        return email;
    }


    // Indicates whether the user's account has expired.
    // In this case, delegate the default implementation to the UserDetails interface method.
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }


    // Indicates whether the user's account is locked.
    // Similar to the previous method, it uses the default implementation.
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }


    // Indicates whether the user's credentials (e.g. password) have expired.
    // Delegated to the default UserDetails method.
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }


    // Indicates whether the user is enabled (active).
    // The default implementation provided by the UserDetails interface is used.
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}
