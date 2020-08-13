package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Product;

public interface ProductService {

    Product save(Product product);

    public void initDb(String dbPath);

    Product findById(Long id);

}
