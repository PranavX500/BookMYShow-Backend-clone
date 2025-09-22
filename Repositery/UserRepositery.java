package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Theater;
import com.example.BookMy_SHow.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositery extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Boolean existingByEmail(String email);
}
