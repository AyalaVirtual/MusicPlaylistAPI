package com.example.miniproject.repository;

import com.example.miniproject.model.Genre;
import com.example.miniproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    // This method retrieves an individual song by its name
    Song findByName(String songName);

    // This method retrieves all songs saved to a user's account
    List<Song> findByUserId(Long userId);

    // This method retrieves an individual song saved to a user's account by song id
    Optional<Song> findByIdAndUserId(Long songId, Long userId);

    // This method retrieves an individual song saved to a user's account by song name
    Song findByNameAndUserId(String songName, Long userId);

}
