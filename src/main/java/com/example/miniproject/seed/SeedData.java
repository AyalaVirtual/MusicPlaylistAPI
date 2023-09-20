package com.example.miniproject.seed;

import com.example.miniproject.model.User;
import com.example.miniproject.model.Genre;
import com.example.miniproject.model.Song;
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


    @Override
    public void run(String... args) throws Exception {

        // In production, we would not have this file!! This is just for testing. The user data must match what we enter in Postman for it to work, or we'll have to create user everytime in Postman before logging in the user
        User user = new User();
        user.setUserName("Korin");
        user.setEmailAddress("enchantress@gmail.com");
        user.setPassword(passwordEncoder.encode("magic7"));
        userRepository.save(user);

        Genre genre = new Genre);
        genre.setName("Emo Rap");
        genre.setDescription("a blend of new age Hip Hop and R&B");
        genre.setUser(user);
        genreRepository.save(genre);

        Song song1 = new Song();
        song1.setGenre(genre);
        song1.setName("Already Dead");
        song1.setArtist("Juice Wrld");
        song1.setAlbumName("Fighting Demons");
        songRepository.save(song1)

    }



}
