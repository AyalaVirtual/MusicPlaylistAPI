package com.example.miniproject.service;

import com.example.miniproject.model.Genre;
import com.example.miniproject.model.User;
import com.example.miniproject.repository.GenreRepository;
import com.example.miniproject.repository.SongRepository;
import com.example.miniproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class GenreService {

    private GenreRepository genreRepository;
    private SongRepository songRepository;


    Logger logger = Logger.getLogger(GenreService.class.getName());


    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public static User getCurrentLoggedInUser() {
        // Whenever you login/user sends jwt key, SecurityContextHolder gets filled with current user's principal, which is the user details/information (whether their account is expired, locked, etc.)
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public Optional getGenre(Long genreId) {
        List<Genre>
    }



}
