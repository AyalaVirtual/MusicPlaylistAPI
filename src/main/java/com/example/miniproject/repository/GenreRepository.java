package com.example.miniproject.repository;

import com.example.miniproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    // This method retrieves an individual genre
    Genre findByName(String genreName);

    // This method retrieves all genres by user
    List<Genre> findByUserId(Long userId);

    // This method retrieves an individual genre by user
    Genre findByIdAndUserId(Long genreId, Long userId);

    Genre findByNameAndUserId(Genre genreObject, Long userId);

}
