package com.example.springtest.repositories;

import com.example.springtest.models.Model;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findByName(String name);

    List<Model> findModelsByNameLike(String like);

    List<Model> findModelsByNameLike(String like, Sort sort);

    @Query("SELECT m.uuid, COUNT(o) FROM Model m LEFT JOIN m.offers o GROUP BY m.uuid ORDER BY COUNT(o) DESC")
    List<Object[]> findPopularModelsByOfferCount();
}
