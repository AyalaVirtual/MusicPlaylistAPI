package com.example.miniproject.controller;

import com.example.miniproject.model.Genre;
import com.example.miniproject.model.Song;
import com.example.miniproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * This sets the path for GET requests for all genres and links to the corresponding method in GenreService
     *
     * @return the result from calling the GET method for all genres in GenreService
     */
    @GetMapping(path = "/genres/") // http://localhost:9092/api/genres/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    /**
     * This sets the path for GET requests for a specific genre and links to the corresponding method in GenreService
     *
     * @param genreId represents the specific genre by id
     * @return the result from calling the GET method for a specific genre in GenreService
     */
    @GetMapping(path = "/genres/{genreId}/") // http://localhost:9092/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenre(genreId);
    }

    /**
     *
     *
     * @param genreObject represents the genre the user is trying to create
     * @return the result from calling the POST genre method in GenreService
     */
    // POST genre
    @PostMapping(path = "/genres/") // http://localhost:9092/api/genres/
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }


    /**
     *
     *
     * @param genreId represents the specific genre by id
     * @return the result from calling the DELETE genre method in GenreService
     */
    // DELETE genre
    @DeleteMapping(path = "/genres/{genreId}") //  http://localhost:9092/api/genres/1/
    public Optional<Genre> deleteGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }

    /**
     *
     *
     * @param genreId represents the specific genre by id
     * @param songObject represents the song the user is trying to create
     * @return the result from calling the POST song method in GenreService
     */
    // POST song to genre
    @PostMapping(path = "/genres/{genreId}/songs/") // http://localhost:9092/api/genres/1/songs/
    public Song createSong(@PathVariable(value = "genreId") Long genreId, @RequestBody Song songObject) {

        return genreService.createSong(genreId, songObject);
    }

    /**
     *
     *
     * @return the result from calling the GET all songs method in GenreService
     */
    // GET all songs
    @GetMapping(path = "/genres/songs/")
    public List<Song> getSongs(){
        return genreService.getSongs();
    }

    /**
     *
     *
     * @param genreId represents the specific genre by id
     * @param songId represents the specific song by id
     * @return the result from calling the GET specific song method in GenreService
     */
    // GET individual song
    @GetMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> getSong(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "songId") Long songId){
        return genreService.getSong(genreId, songId);
    }

    /**
     *
     *
     * @param genreId represents the specific genre by id
     * @param songId represents the specific song by id
     * @return the result from calling the DELETE song method in GenreService
     */
    // DELETE song
    @DeleteMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> deleteSong(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "songId") Long songId) {
        return genreService.deleteSong(genreId, songId);
    }

}
