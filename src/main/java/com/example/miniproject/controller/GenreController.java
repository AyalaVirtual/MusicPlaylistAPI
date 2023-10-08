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
    @GetMapping(path = "/genres/") //
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    /**
     * This sets the path for GET requests for a specific genre and links to the corresponding method in GenreService
     *
     * @param genreId represents the id of a specific genre 
     * @return the result from calling the GET method for a specific genre in GenreService
     */
    @GetMapping(path = "/genres/{genreId}/") //
    public Optional<Genre> getGenreById(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    /**
     * This sets the path for POST requests for a genre and links to the corresponding method in GenreService
     *
     * @param genreObject represents the genre the user is trying to create
     * @return the result from calling the POST genre method in GenreService
     */
    @PostMapping(path = "/genres/") //
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }


    @PutMapping(path = "/genres/{genreId}/") //
    public Optional<Genre> updateGenre(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject) {
        return genreService.updateGenre(genreId, genreObject);
    }


    /**
     * This sets the path for DELETE requests for a genre and links to the corresponding method in GenreService
     *
     * @param genreId represents the id of a specific genre 
     * @return the result from calling the DELETE genre method in GenreService
     */
    @DeleteMapping(path = "/genres/{genreId}") //
    public Optional<Genre> deleteGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }

    /**
     * This sets the path for GET requests for all songs and links to the corresponding method in GenreService
     *
     * @return the result from calling the GET all songs method in GenreService
     */
    @GetMapping(path = "/genres/songs/")
    public List<Song> getAllSongs(){
        return genreService.getAllSongs();
    }

    /**
     * This sets the path for GET requests for a specific song and links to the corresponding method in GenreService
     *
     * @param genreId represents the id of a specific genre 
     * @param songId represents the id of a specific song 
     * @return the result from calling the GET specific song method in GenreService
     */
    @GetMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> getSongById(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "songId") Long songId){
        return genreService.getSongById(genreId, songId);
    }

    /**
     * This sets the path for POST requests for a song and links to the corresponding method in GenreService
     *
     * @param genreId represents the id of a specific genre
     * @param songObject represents the song the user is trying to create
     * @return the result from calling the POST song method in GenreService
     */
    @PostMapping(path = "/genres/{genreId}/songs/")
    public Song createSong(@PathVariable(value = "genreId") Long genreId, @RequestBody Song songObject) {

        return genreService.createSong(genreId, songObject);
    }

    /**
     *
     *
     * @param songId
     * @param songObject
     * @return
     */
    @PutMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> updateSong(@PathVariable(value = "songId") Long songId, @RequestBody Song songObject) {
        return genreService.updateSong(songId, songObject);
    }

    /**
     * This sets the path for DELETE requests for a song and links to the corresponding method in GenreService
     *
     * @param genreId represents the id of a specific genre 
     * @param songId represents the id of a specific song 
     * @return the result from calling the DELETE song method in GenreService
     */
    @DeleteMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> deleteSong(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "songId") Long songId) {
        return genreService.deleteSong(genreId, songId);
    }

}
