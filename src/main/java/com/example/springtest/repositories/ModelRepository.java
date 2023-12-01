package com.example.springtest.repositories;

import com.example.springtest.models.Brand;
import com.example.springtest.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findByName(String name);
}
