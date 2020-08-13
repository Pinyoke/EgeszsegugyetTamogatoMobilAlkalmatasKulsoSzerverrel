package com.springserver.server.repository;

import com.springserver.server.model.Product;
import com.springserver.server.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    boolean existsSellerByUrl(String url);

    Seller findByUrl(String url);

    List<Seller> findAll();

    Seller findByName(String name);

    Page<Seller> findByNameContaining(String name, Pageable pageable);
}
