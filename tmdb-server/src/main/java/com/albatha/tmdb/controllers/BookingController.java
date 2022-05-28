package com.albatha.tmdb.controllers;

import java.util.Date;
import java.util.List;

import com.albatha.tmdb.models.Booking;
import com.albatha.tmdb.models.User;
import com.albatha.tmdb.services.interfaces.BookingService;
import com.albatha.tmdb.services.interfaces.UserService;
import com.albatha.tmdb.utils.exceptions.BookingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {
     
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;
    
    @CrossOrigin
    @GetMapping("/create/{userEmail}/{movieId}/{numberOfSeats}/{date}/{isActive}")
    public void createBookingWithExistingUser(@PathVariable String userEmail, @PathVariable String movieId,
            @PathVariable int numberOfSeats, @PathVariable Date date, @PathVariable boolean isActive){
        User user = userService.getUserByEmail(userEmail);
        try {
            bookingService.createBookingWithExistingUser(user, movieId, numberOfSeats, date, isActive);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BookingException(e.toString());
        }   
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @CrossOrigin
    @GetMapping("/id/{bookingId}")
    public Booking getBookingById(@PathVariable long bookingId){
        return bookingService.findBookingById(bookingId);
    }

    @CrossOrigin
    @GetMapping("/all/email/{email}")
    public List<Booking> getAllBookingsByUserEmail(@PathVariable String userEmail){
        return bookingService.getAllBookingsByUserEmail(userEmail);
    }

    @CrossOrigin
    @GetMapping("/all/email/id/{email}/{movieId}")
    public List<Booking> getAllBookingsByUserEmailAndMovieId(@PathVariable String userEmail, @PathVariable String movieId){
        return bookingService.getAllBookingsByUserEmailAndMovieId(userEmail, movieId);
    }

    @CrossOrigin
    @PutMapping("/cancel/{bookingId}")
    public void cancelBookingById(@PathVariable long bookingId){
        bookingService.cancelBookingById(bookingId);
    }

    @CrossOrigin
    @GetMapping("/update/{booking}")
    public void updateBookingById(@PathVariable long bookingId){
        bookingService.updateBookingById(bookingId);
    }

    @CrossOrigin
    @GetMapping("/search/{keyword}")
    public List<Booking> searchBookingByKeyword(@PathVariable String keyword){
        return bookingService.searchBookingListByKeyword(keyword);
    }

    // TODO : public List<Booking> getBookingsByDateFilterRange(Date firstDate, Date lastDate);
}
