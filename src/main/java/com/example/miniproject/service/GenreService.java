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
     * This gets current user's principal (this is their details/information, such as whether their account is expired, locked, etc.) from SecurityContextHolder whenever the user logs in/sends jwt key and returns the current logged in user
     *
     * @return the details of the user who is currently logged in
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }


    /**
     * This is a GET request that checks to see if the list of music genres is empty before either throwing an InformationNotFoundException, or  returning the list of genres
     *
     * @return a list of all music genres
     */
    public List<Genre> getAllGenres() {
        List<Genre> genreList = genreRepository.findByUserId(GenreService.getCurrentLoggedInUser().getId());

        if (genreList.isEmpty()) {
            throw new InformationNotFoundException("no genres found for user id");
        } else {
            return genreList;
        }
    }

    /**
     * This is a GET request that checks to see if an individual genre exists before either returning it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre of music
     * @return genre by id if it exists
     */
    public Optional getGenreById(Long genreId) {
        Optional<Genre> genreOptional = genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId());

        if (genreOptional.isPresent()) {
            return genreOptional;
        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
        }
    }

    /**
     * This is a POST request that checks to see if a genre already exists before either throwing an InformationExistException, or saving the newly created genre to the repository
     *
     * @param genreObject represents the genre the user is trying to create
     * @return newly created genre
     */
    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByNameAndUserId(genreObject.getName(), GenreService.getCurrentLoggedInUser().getId());

        if (genre != null ) {
            throw new InformationExistException("genre with name " + genreObject.getName() + " already exists");
        } else {
            genreObject.setUser(GenreService.getCurrentLoggedInUser());
            return genreRepository.save(genreObject);
        }
    }


    /**
     * This is a PUT request that checks to see if a genre exists before either throwing an InformationNotFoundException, or saving the newly updated genre to the repository
     *
     * @param genreId represents the id of the genre the user is trying to update
     * @param genreObject represents the genre the user is trying to update
     * @return the newly updated genre
     */
    public Optional<Genre> updateGenre(Long genreId, Genre genreObject) {

        Optional<Genre> genreOptional = genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId());

        if (genreOptional.isPresent()) {

            genreOptional.get().setName(genreObject.getName());
            genreOptional.get().setDescription(genreObject.getDescription());

            genreOptional.get().setUser(GenreService.getCurrentLoggedInUser());
            genreRepository.save(genreOptional.get());
            return genreOptional;

        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found.");
        }
    }


    /**
     * This is a DELETE request that checks to see if an individual genre exists before either deleting it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre of music
     * @return the deleted genre
     */
    public Optional<Genre> deleteGenre(Long genreId) {

        Optional<Genre> genreOptional = genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId());

        if (genreOptional.isPresent()) {
            genreRepository.deleteById(genreId);
            return genreOptional;
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }


    /**
     * This is a GET request that returns a list of all songs saved to a user's account
     *
     * @return all songs
     */
    public List<Song> getAllSongs() {
        List<Song> songList = songRepository.findByUserId(GenreService.getCurrentLoggedInUser().getId());

        if (songList.isEmpty()) {
            throw new InformationNotFoundException("no songs found for user id");
        } else {
            return songList;
        }
    }


    /**
     * This is a GET request that checks to see if an individual song exists before either returning it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre 
     * @param songId represents the id of a specific song 
     * @return song by id if it exists
     */
    public Optional<Song> getSongById(Long genreId, Long songId){

        Optional<Song> songOptional = songRepository.findByIdAndUserId(songId, GenreService.getCurrentLoggedInUser().getId());

        Optional<Genre> genreOptional = genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId());

        if (songOptional.isPresent() && genreOptional.get().getSongList().contains(songOptional.get())) {
            return songOptional;
        }else {
            throw new InformationNotFoundException("song with id " + songId + " not found");
        }
    }


    /**
     * This is a POST request that checks to see if the genre the user is trying to create a song in already exists before either throwing an InformationNotFoundException, or moving on to check if the song already exists. From there, it either saves the newly created song to the repository, or throws an InformationExistException
     *
     * @param genreId represents the id of a specific genre of music
     * @param songObject represents the song the user is trying to create
     * @return the newly created song
     */
    public Song createSong(Long genreId, Song songObject) {

        Optional<Genre> genreOptional = genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId());

        Song song = songRepository.findByNameAndUserId(songObject.getName(), GenreService.getCurrentLoggedInUser().getId());

        if (genreOptional.isEmpty()) {

            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        } else if (song != null) {

            throw new InformationExistException("song with name " + songObject.getName() + " already exists");
        } else {

            songObject.setGenre(genreOptional.get());
            songObject.setUser(GenreService.getCurrentLoggedInUser());
            List<Song> songList = genreOptional.get().getSongList();
            genreOptional.get().addToSongList(songObject);
            return songRepository.save(songObject);
        }
    }


    /**
     * This method checks the song repository to see if the song the user is trying to update exists before either throwing an InformationNotFoundException, or saving the newly updated song to the song repository.
     *
     * @param songId represents the id of the song the user is trying to update
     * @param songObject represents the updated version of the song that the user is trying to update
     * @return the individual song if it exists
     */
    public Optional<Song> updateSong(Long songId, Song songObject) {

        Optional<Song> songOptional = songRepository.findByIdAndUserId(songId, GenreService.getCurrentLoggedInUser().getId());

        if (songOptional.isPresent()) {

            songOptional.get().setName(songObject.getName());
            songOptional.get().setArtist(songObject.getArtist());
            songOptional.get().setAlbumName(songObject.getAlbumName());
            songOptional.get().setGenre(songObject.getGenre());

            songRepository.save(songOptional.get());
            return songOptional;
        } else {
            throw new InformationNotFoundException("song with id " + songId + " not found");
        }
    }


    /**
     * This is a DELETE request that checks to see if an individual song exists before either deleting it, or throwing an InformationNotFoundException
     *
     * @param genreId represents the id of a specific genre 
     * @param songId represents the id of a specific song 
     * @return the deleted song
     */
    public Optional<Song> deleteSong(Long genreId, Long songId){
        Optional<Song> songOptional = songRepository.findByIdAndUserId(songId, GenreService.getCurrentLoggedInUser().getId());

        if (songOptional.isPresent()){
            songRepository.deleteById(songId);
            return songOptional;
        } else {
            throw new InformationNotFoundException("song with id " + songId + " not found");
        }
    }

}
