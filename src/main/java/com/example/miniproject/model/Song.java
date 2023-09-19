package com.example.miniproject.model;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String artist;

    @Column
    private String albumName;





    public Song() {
    }

    public Song(Long id, String name, String artist, String albumName) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.albumName = albumName;
    }







}
