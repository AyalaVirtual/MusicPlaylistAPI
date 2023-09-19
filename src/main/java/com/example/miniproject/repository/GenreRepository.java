package com.example.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository {

    // This method retrieves an individual genre
    Genre findByName(String genreName);

    // This method retrieves all genres by user
    List<Genre> findByUserId(Long userId);

    // This method retrieves an individual genre by user
    Genre findByIdAndUserId(Long genreId, Long userId);




}
