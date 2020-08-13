package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Seller;

public interface SellerService {

    Seller save(Seller seller);

    boolean existSellerByUrl(String url);

    Seller getSellerByUrl(String url);
}
