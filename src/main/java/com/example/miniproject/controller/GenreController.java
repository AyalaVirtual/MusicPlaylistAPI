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

    // GET all genres
    @GetMapping(path = "/genres/") // http://localhost:9092/api/genres/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    // GET individual genre
    @GetMapping(path = "/genres/{genreId}/") // http://localhost:9092/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenre(genreId);
    }

    // POST genre
    @PostMapping(path = "/genres/") // http://localhost:9092/api/genres/
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }










    // DELETE genre
    @DeleteMapping(path = "/genres/{genreId}") //  http://localhost:9092/api/genres/1/
    public Optional<Genre> deleteGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }


    // POST song to genre
    @PostMapping(path = "/genres/{genreId}/songs/") // http://localhost:9092/api/genres/1/songs/
    public Song createSong(@PathVariable(value = "genreId") Long genreId, @RequestBody Song songObject) {

        return genreService.createSong(genreId, songObject);
    }

    // GET all songs
    @GetMapping(path = "/genres/songs/")
    public List<Song> getSongs(){
        return genreService.getSongs();
    }

    // GET individual song
    @GetMapping(path = "/genres/{genreId}/songs/{songId}/")
    public Optional<Song> getSong(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "songId") Long songId){
        return genreService.getSong(genreId, songId);
    }







}
