package com.example.miniproject.controller;

import com.example.miniproject.model.Genre;
import com.example.miniproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(/api/)
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping(path = "/genres/") // http://localhost:9092/api/genres/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping(path = "/genres/{genreId}/") // http://localhost:9092/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenre(genreId);
    }


}
