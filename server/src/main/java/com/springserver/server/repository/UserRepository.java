package com.springserver.server.repository;

import com.springserver.server.model.Product;
import com.springserver.server.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

    Page<User> findByEmailContaining(String email, Pageable pageable);


}
