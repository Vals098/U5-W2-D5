package valeriafarinosi.U5_W2_D5.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.U5_W2_D5.entities.Booking;
import valeriafarinosi.U5_W2_D5.exceptions.ValidationException;
import valeriafarinosi.U5_W2_D5.payloads.BookingResponseDTO;
import valeriafarinosi.U5_W2_D5.payloads.NewBookingDTO;
import valeriafarinosi.U5_W2_D5.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    //    BookingService's DI
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //    POST http://localhost:3003/bookings + payload -> newBooking
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@RequestBody @Validated NewBookingDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }

        Booking saved = this.bookingService.saveBooking(payload);
        return new BookingResponseDTO(saved.getBookingId());


    }


}
