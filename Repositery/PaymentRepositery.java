package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Movie;
import com.example.BookMy_SHow.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositery extends JpaRepository<Payment,Long> {

    Optional<Payment> findByTransactionId(String transactionId);


}
