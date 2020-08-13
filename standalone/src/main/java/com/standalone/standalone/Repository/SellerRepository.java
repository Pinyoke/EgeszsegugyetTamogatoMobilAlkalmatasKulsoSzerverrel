package com.standalone.standalone.Repository;


import com.standalone.standalone.Entity.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, Long> {

    boolean existsSellerByUrl(String url);

    Seller findByUrl(String url);
}
