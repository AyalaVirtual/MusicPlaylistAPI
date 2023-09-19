package com.example.miniproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // This means that users can only write to this property, not read it
    private String password;







}
