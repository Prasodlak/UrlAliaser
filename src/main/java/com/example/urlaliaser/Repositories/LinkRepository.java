package com.example.urlaliaser.Repositories;

import com.example.urlaliaser.Models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByAlias(String alias);
}
