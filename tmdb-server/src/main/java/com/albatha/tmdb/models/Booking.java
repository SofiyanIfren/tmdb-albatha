package com.albatha.tmdb.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    
	@Id
    @Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long	id;
	@Column(name = "number_of_seats")
	private int 	numberOfSeats;
	@Column(name = "date")
	private Date 	date;
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "movie_id")
	private String	movieId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	public Booking(int numberOfSeats, Date date, String movieId, User user, boolean isActive) {
		this.numberOfSeats 	= numberOfSeats;
		this.date 			= date;
		this.movieId 		= movieId;
		this.user 			= user;
		this.isActive		= isActive;
	}
}
