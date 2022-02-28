package com.melhem.hicarttask.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "user_hicart_task")
@EntityListeners(AuditingEntityListener.class)
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @NotNull
    private String email;
    @NotNull
    private String gender;
    private int phone;
    @NotNull
    @JsonIgnore
    private String password;
    private Date created;


    @NotNull

    private String name;
    @NotNull

    private String address;











    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }




    public AppUser() {}

    public AppUser(@NotNull String email, @NotNull  String password, @NotNull String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.created = new Date();
    }
    public AppUser(@NotNull String email, @NotNull  String password, @NotNull String name,@NotNull String gender,@NotNull String address , int phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender=gender;
        this.address=address;
        this.phone=phone;

        this.created = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
