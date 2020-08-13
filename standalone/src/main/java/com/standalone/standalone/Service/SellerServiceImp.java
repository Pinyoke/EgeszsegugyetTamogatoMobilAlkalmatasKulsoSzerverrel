package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Seller;
import com.standalone.standalone.Repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp implements SellerService{

    private final SellerRepository sellerRepository;

    public SellerServiceImp(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public boolean existSellerByUrl(String url) {
        return sellerRepository.existsSellerByUrl(url);
    }

    @Override
    public Seller getSellerByUrl(String url) {
        return sellerRepository.findByUrl(url);
    }
}
