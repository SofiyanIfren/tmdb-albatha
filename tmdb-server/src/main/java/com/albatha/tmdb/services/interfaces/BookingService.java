package com.albatha.tmdb.services.interfaces;

import java.util.Date;
import java.util.List;

import com.albatha.tmdb.models.Booking;
import com.albatha.tmdb.models.User;

public interface BookingService {
    
   Booking createBookingWithExistingUser(User user, String movieId, int numberOfSeats, Date date, boolean isActive);
   List<Booking> getAllBookingsByUserEmail(String userEmail);
   List<Booking> getAllBookingsByUserEmailAndMovieId(String userEmail, String movieId);
   Booking getBookingById(long bookingId);
   void cancelBookingById(long bookingId);

   List<Booking> getAllBookings();
   void updateBookingById(long bookingId);

   List<Booking> getBookingListByDateFilterRange(Date firstDate, Date lastDate);
   List<Booking> searchBookingListByKeyword(String keyword);

   void deleteBookingById(long bookingId);
   Booking findBookingById(long bookingId);
    
}
