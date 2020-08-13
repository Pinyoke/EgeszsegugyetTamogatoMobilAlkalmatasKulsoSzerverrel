package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Offer;
import com.standalone.standalone.Repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImp implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImp(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }
}
