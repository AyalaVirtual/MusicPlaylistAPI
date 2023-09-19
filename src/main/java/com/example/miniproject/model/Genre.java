package com.example.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column
    private String genreName;

    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "genre", orphanRemoval = true) // This means it's a one-to-many relationship that is mappedBy the variable representing the link to the other table. orphanRemoval = true means that if we delete the genre, then to delete the song as well
    @LazyCollection(LazyCollectionOption.FALSE) // This means when you fetch an instance of a genre, fetch the associated songs
    private List<Song> songList;


    public Genre() {

    }

    public Genre(Long genreId, String genreName, String description, User user, List<Song> songList) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.description = description;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
