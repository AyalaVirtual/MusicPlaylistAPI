package com.example.miniproject.service;

import com.example.miniproject.exception.InformationExistException;
import com.example.miniproject.exception.InformationNotFoundException;
import com.example.miniproject.model.Genre;
import com.example.miniproject.model.Song;
import com.example.miniproject.model.User;
import com.example.miniproject.repository.GenreRepository;
import com.example.miniproject.repository.SongRepository;
import com.example.miniproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    /**
     * This gets current user's principal (details/information (whether their account is expired, locked, etc.) from SecurityContextHolder returns the current logged in user
     *
     * @return the details of the user who is currently logged in
     */
    public static User getCurrentLoggedInUser() {
        // Whenever you login/user sends jwt key, SecurityContextHolder gets filled with current user's principal, which is the user details/information (whether their account is expired, locked, etc.)
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * This is a GET request that checks to see if an individual genre exists before either returning it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre of music
     * @return genre by id if it exists
     */
    public Optional getGenre(Long genreId) {
        Optional<Genre> genreOptional = Optional.of(genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId()));

        if (genreOptional.isPresent()) {
            return genreOptional;
        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
        }
    }

    /**
     * This is a GET request that checks to see if the list of music genres is empty before either throwing an InformationNotFoundException, or  returning the list of genres
     *
     * @return a list of all music genres
     */
    public List<Genre> getGenres() {
        List<Genre> genreList = genreRepository.findByUserId(GenreService.getCurrentLoggedInUser().getId());

        if (genreList.isEmpty()) {
            throw new InformationNotFoundException("no genres found for user id");
        } else {
            return genreList;
        }
    }

    /**
     * This is a POST request that checks to see if a genre already exists before either throwing an InformationExistException, or saving the newly created genre to the repository
     *
     * @param genreObject represents the genre the user is trying to create
     * @return newly created genre
     */
    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByName(genreObject.getName());

        if (genre != null) {
            throw new InformationExistException("genre with name " + genreObject.getName() + " already exists");
        } else {
            genreObject.setUser(GenreService.getCurrentLoggedInUser());
            return genreRepository.save(genreObject);
        }
    }


    /**
     * This is a DELETE request that checks to see if an individual genre exists before either deleting it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre of music
     * @return the deleted genre
     */
    public Optional<Genre> deleteGenre(Long genreId) {
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isPresent()) {
            genreRepository.deleteById(genreId);
            return genreOptional;
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }


    /**
     * This is a POST request that checks to see if the genre the user is trying to create a song in already exists before either saving the newly created song to the repository, or throwing an InformationExistException
     *
     * @param genreId represents the id of a specific genre of music
     * @param songObject represents the song the user is trying to create
     * @return the newly created song
     */
    public Song createSong(Long genreId, Song songObject) {

        try {
            Optional<Genre> genreOptional = genreRepository.findById(genreId);
            songObject.setGenre(genreOptional.get());
            return songRepository.save(songObject);

        } catch (InformationNotFoundException e) {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }

    /**
     * This is a GET request that returns a list of all songs
     *
     * @return all songs
     */
    public List<Song> getSongs(){
        return songRepository.findAll();
    }


    /**
     * This is a GET request that checks to see if an individual song exists before either returning it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the specific genre by id
     * @param songId represents the specific song by id
     * @return song by id if it exists
     */
    public Optional<Song> getSong(Long genreId, Long songId){
        Optional<Song> songOptional = songRepository.findById(songId);
        Optional<Genre> genreOptional = genreRepository.findById((genreId));

        if (songOptional.isPresent()){
            return songOptional;
        }else {
            throw new InformationNotFoundException("category with id ");
        }
    }


    /**
     * This is a DELETE request that checks to see if an individual song exists before either deleting it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the specific genre by id
     * @param songId represents the specific song by id
     * @return the deleted song
     */
    public Optional<Song> deleteSong(Long genreId, Long songId){
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        Optional<Song> songOptional = songRepository.findById(songId);

        if (songOptional.isPresent()){
            songRepository.deleteById(songId);
            return songOptional;
        } else {
            throw new InformationNotFoundException("song with id " + songId + " not found");
        }
    }

}
