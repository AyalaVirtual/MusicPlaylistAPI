package com.example.miniproject.repository;

import com.example.miniproject.model.Genre;
import com.example.miniproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    // This method retrieves an individual song
    Song findByName(String songName);

    // This method retrieves all songs by user
    List<Song> findByUserId(Long userId);

    // This method retrieves an individual song by user
    Song findByIdAndUserId(Long songId, Long userId);

    Song findByNameAndUserId(Song songObject, Long userId);

}
