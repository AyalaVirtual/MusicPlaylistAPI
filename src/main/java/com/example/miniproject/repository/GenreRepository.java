package com.example.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository {

    Genre findByName(String name);


}
