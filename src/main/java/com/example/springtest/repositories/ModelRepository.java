package com.example.springtest.repositories;

import com.example.springtest.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ModelRepository extends JpaRepository<Model, UUID> {
}
