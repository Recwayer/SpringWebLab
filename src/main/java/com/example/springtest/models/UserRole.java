package com.example.springtest.models;

import com.example.springtest.models.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "roles")
public class UserRole {

    private UUID uuid;

    private Role role;

    private List<User> users;


    protected UserRole() {
    }

    public UserRole(UUID uuid, Role role, List<User> users) {
        this.uuid = uuid;
        this.role = role;
        this.users = users;
    }

    public UserRole(Role role) {
        this.role = role;
    }

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    public UUID getUuid() {
        return this.uuid;
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public Role getRole() {
        return this.role;
    }

    @OneToMany(mappedBy = "role", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    public List<User> getUsers() {
        return this.users;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
