package com.example.miniproject.seed;

import com.example.miniproject.model.User;
import com.example.miniproject.repository.GenreRepository;
import com.example.miniproject.repository.SongRepository;
import com.example.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final SongRepository songRepository;


    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, GenreRepository genreRepository, SongRepository songRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.songRepository = songRepository;
    }



}
