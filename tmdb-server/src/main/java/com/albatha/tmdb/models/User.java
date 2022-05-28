package com.albatha.tmdb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long	id;
    @Column(name = "email", unique=true)
	private String 	email;
    @Column(name = "first_name")
	private String 	firstName;
    @Column(name = "last_name")
	private String 	lastName;
    
    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
