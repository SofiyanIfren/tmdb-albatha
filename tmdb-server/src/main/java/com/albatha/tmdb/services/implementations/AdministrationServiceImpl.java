package com.albatha.tmdb.services.implementations;

import java.util.Calendar;
import java.util.List;

import com.albatha.tmdb.models.Booking;
import com.albatha.tmdb.models.User;
import com.albatha.tmdb.services.interfaces.BookingService;
import com.albatha.tmdb.services.interfaces.AdministrationService;
import com.albatha.tmdb.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="populateDatabaseService")
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Override
    public void createFakeDatas() {
        Calendar calendar = Calendar.getInstance(); 
        User user_001 = userService.createUser(new User("user_001@albatha.com", "firstName_001", "lastName_001"));
        User user_002 = userService.createUser(new User("user_002@albatha.com", "firstName_002", "lastName_002"));
        User user_003 = userService.createUser(new User("user_003@albatha.com", "firstName_003", "lastName_003"));
        User user_004 = userService.createUser(new User("user_004@albatha.com", "firstName_004", "lastName_004"));
        bookingService.createBookingWithExistingUser(user_001, "752623", 2, calendar.getTime(), true);
        bookingService.createBookingWithExistingUser(user_002, "526896", 2, calendar.getTime(), true);
        bookingService.createBookingWithExistingUser(user_003, "639933", 2, calendar.getTime(), true);
        bookingService.createBookingWithExistingUser(user_004, "639933", 2, calendar.getTime(), true);
    }

    @Override
    public void deleteFakeDatas() {
        List<Booking> bookingList = bookingService.getAllBookingsByUserEmail("user_001@albatha.com");
        bookingList.addAll(bookingService.getAllBookingsByUserEmail("user_002@albatha.com"));
        bookingList.addAll(bookingService.getAllBookingsByUserEmail("user_003@albatha.com"));
        bookingList.addAll(bookingService.getAllBookingsByUserEmail("user_004@albatha.com"));
        bookingList.forEach(booking -> bookingService.deleteBookingById(booking.getId()));
        userService.deleteUserById(userService.getUserByEmail("user_001@albatha.com").getId());
        userService.deleteUserById(userService.getUserByEmail("user_002@albatha.com").getId());
        userService.deleteUserById(userService.getUserByEmail("user_003@albatha.com").getId());
        userService.deleteUserById(userService.getUserByEmail("user_004@albatha.com").getId());
    }
}
