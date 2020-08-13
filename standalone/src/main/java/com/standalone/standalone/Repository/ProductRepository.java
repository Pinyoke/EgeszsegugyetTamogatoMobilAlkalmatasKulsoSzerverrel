package com.standalone.standalone.Repository;


import com.standalone.standalone.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {



}
