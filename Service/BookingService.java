package com.example.BookMy_SHow.Service;

import com.example.BookMy_SHow.Dto.*;
import com.example.BookMy_SHow.Model.*;
import com.example.BookMy_SHow.Repositery.BookingRepo;
import com.example.BookMy_SHow.Repositery.ShowRepositery;
import com.example.BookMy_SHow.Repositery.ShowSeatRepositery;
import com.example.BookMy_SHow.Repositery.UserRepositery;
import com.example.BookMy_SHow.exception.ResourceNotFoundException;
import com.example.BookMy_SHow.exception.SeatUnavailableException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private UserRepositery userRepositery;
    @Autowired
    private ShowRepositery showRepositery;
    @Autowired
    private ShowSeatRepositery showSeatRepositery;
    @Autowired
    private BookingRepo bookingRepo;
    public BookingDto createBooking(BookingRequestDto bookingRequestDto) throws SeatUnavailableException {
        User user=userRepositery.findById(bookingRequestDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Show show=showRepositery.findById(bookingRequestDto.getShowId())
                .orElseThrow(()->new ResourceNotFoundException("Show not found"));
        List<ShowSeat> selectedSeats=showSeatRepositery.findAllById(bookingRequestDto.getSeatIds());
        for(ShowSeat seat:selectedSeats){
            if(!"AVAILABLE".equals(seat.getStatus())){
                throw new SeatUnavailableException("Seat"+seat.getSeat().getSeatNumber()+"is not available");
            }
            seat.setStatus("Locked");
        }
        showSeatRepositery.saveAll(selectedSeats);

        Double totalAmount=selectedSeats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        Payment payment=new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());

        Booking booking=new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("Confirmed");
        booking.setTotalAmount(totalAmount);
        booking.setPayment(payment);

        Booking saveBooking=bookingRepo.save(booking);
        selectedSeats.forEach(seat->{
            seat.setStatus("BOOKED");
            seat.setBooking(saveBooking);
        });
        showSeatRepositery.saveAll(selectedSeats);

        return maptoBookingDto(saveBooking,selectedSeats);


    }
    public BookingDto getBookingById(Long id){
        Booking booking=bookingRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not Found"));
     List<ShowSeat> seats=  showSeatRepositery.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null&&seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());
        return  maptoBookingDto(booking,seats);



    }
    public BookingDto getBookingByNumber(String bookingNumber){
        Booking booking=bookingRepo.findByBookingNumber(bookingNumber)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not Found"));
        List<ShowSeat> seats=  showSeatRepositery.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null&&seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());
        return  maptoBookingDto(booking,seats);



    }

    private List<BookingDto> getBookingByUserId(Long userId){
     List<Booking>bookings=bookingRepo.findByUserId(userId);
      return bookings.stream()
              .map(booking -> {
                  List<ShowSeat>seats=showSeatRepositery.findAll()
                          .stream()
                          .filter(seat->seat.getBooking()!=null&&seat.getBooking().getId().equals(booking.getId()))
                          .collect(Collectors.toList());
                  return  maptoBookingDto(booking,seats);
              })
              .collect(Collectors.toList());



    }
    @Transactional
    public BookingDto cancelBooking(Long id){
        Booking booking=bookingRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not found"));
        booking.setStatus("Cancelled");
        List<ShowSeat> seats=  showSeatRepositery.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null&&seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());

        seats.forEach(seat->{
            seat.setStatus("AVAILABLE");
            seat.setBooking(null);
        });
        if(booking.getPayment()!=null){
            booking.getPayment().setStatus("REFUNDED");
        }
        Booking updatedBooking=bookingRepo.save(booking);
        showSeatRepositery.saveAll(seats);
        return maptoBookingDto(updatedBooking,seats);
    }



    @Transactional
    private BookingDto maptoBookingDto(Booking booking ,List<ShowSeat>seats){
        BookingDto bookingDto=new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setBookingNumber(String.valueOf(booking.getBookingTime()));
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setStatus(bookingDto.getStatus());
        bookingDto.setTotalAmount(booking.getTotalAmount());

        UserDto userDto=new UserDto();
        userDto.setId(booking.getUser().getId());
        userDto.setName(booking.getUser().getName());
        userDto.setEmail(booking.getUser().getEmail());
        userDto.setPhoneNumber(booking.getUser().getPhoneNumber());

        ShowDto showDto= new ShowDto();
        showDto.setId(booking.getShow().getId());
        showDto.setStartTime(booking.getShow().getStartTime());
        showDto.setEndTime(booking.getShow().getEndTime());

        MovieDto movieDto=new MovieDto();

        movieDto.setId(booking.getShow().getMovie().getId());
        movieDto.setTitle(booking.getShow().getMovie().getTitle());
        movieDto.setDescription(booking.getShow().getMovie().getDescription());
        movieDto.setLanguage(booking.getShow().getMovie().getLanguage());
        movieDto.setGenre(booking.getShow().getMovie().getGenre());
        movieDto.setDurationMins(booking.getShow().getMovie().getDurationMins());
        movieDto.setReleaseDate(booking.getShow().getMovie().getReleaseDate());
        movieDto.setDurationMins(Integer.valueOf(booking.getShow().getMovie().getDurationMins()));
        movieDto.setPosterUrl(booking.getShow().getMovie().getPosterUrl());
        showDto.setMovie(movieDto);

        ScreenDto screenDto=new ScreenDto();
        screenDto.setId(booking .getShow().getScreen().getId());
        screenDto.setName(booking .getShow().getScreen().getName());
        screenDto.setTotalSeats(booking.getShow().getScreen().getTotalSeats());

        TheaterDto theaterDto=new TheaterDto();
        theaterDto.setId(bookingDto.getShow().getScreen().getTheater().getId());
        theaterDto.setName(bookingDto.getShow().getScreen().getTheater().getName());
        theaterDto.setAddress(bookingDto.getShow().getScreen().getTheater().getAddress());
        theaterDto.setCity(bookingDto.getShow().getScreen().getTheater().getAddress());
        theaterDto.setTotalScreen(bookingDto.getShow().getScreen().getTheater().getTotalScreen());


        screenDto.setTheater(theaterDto);
        showDto.setScreen(screenDto);
        bookingDto.setShow(showDto);

    List<ShowSeatDto> seatDtos=  seats.stream()
                .map(seat->{
                    ShowSeatDto seatDto=new ShowSeatDto();
                    seatDto.setId(seat.getId());
                    seatDto.setStatus(seat.getStatus());
                    seatDto.setPrice(seat.getPrice());

                    SeatDto baseSeatDto=new SeatDto();
                    baseSeatDto.setId(seat.getSeat().getId());
                    baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                    baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                    baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());
                    seatDto.setSeat(baseSeatDto);
                     return  seatDto;



                })
            .collect(Collectors.toList());
    bookingDto.setSeats(seatDtos);

        if (booking.getPayment() != null) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(booking.getPayment().getId());
            paymentDto.setAmount(booking.getPayment().getAmount());
            paymentDto.setPaymentMethod(booking.getPayment().getPaymentMethod());
            paymentDto.setPaymentTime(booking.getPayment().getPaymentTime());
            paymentDto.setStatus(booking.getPayment().getStatus());
            paymentDto.setTransactionId(booking.getPayment().getTransactionId());

            bookingDto.setPayment(paymentDto);
        }

        return bookingDto;









    }
}