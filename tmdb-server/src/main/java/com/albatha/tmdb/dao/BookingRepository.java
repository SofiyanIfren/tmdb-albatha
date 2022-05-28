package com.albatha.tmdb.dao;

import java.util.List;

import com.albatha.tmdb.models.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    List<Booking> findBookingListByUserEmail(String userEmail);
    List<Booking> findBookingListByUserEmailAndMovieId(String userEmail, String movieId);
    
    List<Booking> findBookingListByUserEmailContaining(String keyword);
    List<Booking> findBookingListByUserFirstNameContaining(String keyword);
    List<Booking> findBookingListByUserLastNameContaining(String keyword);
    
}
