package org.uce.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    public String fullName;

    public String phoneNumber;
    public String address;

    @Enumerated(EnumType.STRING)
    public Role role;

    public boolean isActive = true;

    public enum Role {
        CUSTOMER, ADMIN, PROVIDER
    }
}