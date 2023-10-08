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
        user.setUserName("musicLuvr");
        user.setEmailAddress("cigana@gmail.com");
        user.setPassword(passwordEncoder.encode("pombagira7"));
        userRepository.save(user);

        // Genre 1
        Genre genre1 = new Genre();
        genre1.setName("Hip Hop/R&B");
        genre1.setDescription("a modern take on the hybridity between hip hop and R&B");
        genre1.setUser(user);
        genreRepository.save(genre1);

        Song song1A = new Song();
        song1A.setGenre(genre1);
        song1A.setName("Already Dead");
        song1A.setArtist("Juice Wrld");
        song1A.setAlbumName("Fighting Demons");
        songRepository.save(song1A);

        Song song1B = new Song();
        song1B.setGenre(genre1);
        song1B.setName("Pillz & Billz");
        song1B.setArtist("Rod Wave");
        song1B.setAlbumName("SoulFly");
        songRepository.save(song1B);


        // Genre 2
        Genre genre2 = new Genre();
        genre2.setName("Rap");
        genre2.setDescription("a rhythmic style of speech with a recurring beat");
        genre2.setUser(user);
        genreRepository.save(genre2);

        Song song2A = new Song();
        song2A.setGenre(genre2);
        song2A.setName("Last Day In");
        song2A.setArtist("Kodak Black");
        song2A.setAlbumName("Last Day In");
        songRepository.save(song2A);

        Song song2B = new Song();
        song2B.setGenre(genre2);
        song2B.setName("772 Love");
        song2B.setArtist("YNW Melly");
        song2B.setAlbumName("Collect Call");
        songRepository.save(song2B);


        // Genre 3
        Genre genre3 = new Genre();
        genre3.setName("Reggaeton");
        genre3.setDescription("a blend of reggae, Latin American dance hall, and hip-hop influences");
        genre3.setUser(user);
        genreRepository.save(genre3);

        Song song3A = new Song();
        song3A.setGenre(genre3);
        song3A.setName("Down");
        song3A.setArtist("RKM y Ken-Y");
        song3A.setAlbumName("Masterpiece");
        songRepository.save(song3A);

        Song song3B = new Song();
        song3B.setGenre(genre3);
        song3B.setName("Hoy lo Siento");
        song3B.setArtist("Zion y Lennox");
        song3B.setAlbumName("Los Verdaderos");
        songRepository.save(song3B);
    }

}
