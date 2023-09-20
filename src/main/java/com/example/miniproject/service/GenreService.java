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
     *
     * @return the details of the user who is currently logged in
     */
    public static User getCurrentLoggedInUser() {
        // Whenever you login/user sends jwt key, SecurityContextHolder gets filled with current user's principal, which is the user details/information (whether their account is expired, locked, etc.)
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * This method is a GET request that checks to see if an individual genre exists before either returning it, or throwing an InformationNotFoundException
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
     * This method is a GET request that checks to see if the list of music genres is empty before either throwing an InformationNotFoundException, or  returning the list of genres
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

    // POST genre
    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByName(genreObject.getName());

        if (genre != null) {
            throw new InformationExistException("genre with name " + genreObject.getName() + " already exists");
        } else {
            genreObject.setUser(GenreService.getCurrentLoggedInUser());
            return genreRepository.save(genreObject);
        }
    }






    // DELETE genre
    public Optional<Genre> deleteGenre(Long genreId) {
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isPresent()) {
            genreRepository.deleteById(genreId);
            return genreOptional;
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }









    // POST song to genre
    public Song createSong(Long genreId, Song songObject) {

        try {
            Optional<Genre> genreOptional = genreRepository.findById(genreId);
            songObject.setGenre(genreOptional.get());
            return songRepository.save(songObject);

        } catch (InformationNotFoundException e) {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }


    // GET all songs
    public List<Song> getSongs(){
        return songRepository.findAll();
    }


    // GET individual song
    public Optional<Song> getSong(Long genreId, Long songId){
        Optional<Song> songOptional = songRepository.findById(songId);
        Optional<Genre> genreOptional = genreRepository.findById((genreId));

        if (songOptional.isPresent()){
            return songOptional;
        }else {
            throw new InformationNotFoundException("category with id ");
        }
    }







    // DELETE song
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
