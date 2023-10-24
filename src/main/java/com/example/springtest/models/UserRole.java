package com.example.springtest.models;

import com.example.springtest.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private UUID uuid;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToMany(mappedBy = "role",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    private List<User> users;


    protected UserRole() {
    }
}
