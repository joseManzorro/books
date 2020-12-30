package com.fortium.bookstore.repository.security;

import com.fortium.bookstore.domain.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}