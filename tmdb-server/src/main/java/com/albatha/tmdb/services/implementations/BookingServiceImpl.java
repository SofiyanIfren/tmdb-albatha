package com.albatha.tmdb.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.albatha.tmdb.dao.BookingRepository;
import com.albatha.tmdb.dao.UserRepository;
import com.albatha.tmdb.models.Booking;
import com.albatha.tmdb.models.User;
import com.albatha.tmdb.services.interfaces.BookingService;
import com.albatha.tmdb.utils.TmdbConstants;
import com.albatha.tmdb.utils.exceptions.BookingException;
import com.albatha.tmdb.utils.exceptions.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="bookingService")
public class BookingServiceImpl implements BookingService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBookingWithExistingUser(User user, String movieId, int numberOfSeats, Date date, boolean isActive){
        Booking booking = new Booking(numberOfSeats, date, movieId, user, isActive);
        if (userRepository.findUserByEmail(user.getEmail()) == null){ // user does not exist
            throw new UserException(TmdbConstants.ERROR_REGISTER_BEFORE_BOOKING);
        } else{ // user exists
            if (!isMovieBookingAllowedForUser(user, movieId))
                throw new BookingException(TmdbConstants.ERROR_MAX_BOOKINGS);
            else
                booking = bookingRepository.save(booking);
        }
        return booking;
    }

    public boolean isMovieBookingAllowedForUser(User user, String movieId){
        int activeUserMovieBookingsQty = 0; // user is not allowed to have 10 or more bookings
        List<Booking> userMovieBookingList = getAllBookingsByUserEmailAndMovieId(user.getEmail(), movieId);
        for (Booking indexBooking : userMovieBookingList){
            if (indexBooking.isActive())        activeUserMovieBookingsQty += 1;
        }
        if (activeUserMovieBookingsQty >= 10)   return false;
        return true;
    }

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBookingById(long bookingId) {
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public List<Booking> getAllBookingsByUserEmail(String userEmail){
        return bookingRepository.findBookingListByUserEmail(userEmail);
    }

    @Override
    public List<Booking> getAllBookingsByUserEmailAndMovieId(String userEmail, String movieId){
        return bookingRepository.findBookingListByUserEmailAndMovieId(userEmail, movieId);
    }


    @Override
    public Booking getBookingById(long bookingId){
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public void updateBookingById(long bookingId){
        Booking booking = bookingRepository.findById(bookingId).get();
        bookingRepository.save(booking);
    }

    @Override
    public void cancelBookingById(long bookingId){
        Booking booking = bookingRepository.findById(bookingId).get();
        booking.setActive(false);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> searchBookingListByKeyword(String keyword){
        List<Booking> bookingList = new ArrayList<>();
        if (!bookingList.containsAll(bookingRepository.findBookingListByUserEmailContaining(keyword)))
            bookingList.addAll(bookingRepository.findBookingListByUserEmailContaining(keyword));
        if (!bookingList.containsAll(bookingRepository.findBookingListByUserFirstNameContaining(keyword)))
            bookingList.addAll(bookingRepository.findBookingListByUserFirstNameContaining(keyword));
        if (!bookingList.containsAll(bookingRepository.findBookingListByUserLastNameContaining(keyword)))
            bookingList.addAll(bookingRepository.findBookingListByUserLastNameContaining(keyword));
        return bookingList;
    }


    @Override
    public List<Booking> getBookingListByDateFilterRange(Date firstDate, Date lastDate){
        // TODO : return booking+ associated user + associated movie
        return null;
    }

    @Override
    public void deleteBookingById(long bookingId){
        bookingRepository.deleteById(bookingId);
    }
}
