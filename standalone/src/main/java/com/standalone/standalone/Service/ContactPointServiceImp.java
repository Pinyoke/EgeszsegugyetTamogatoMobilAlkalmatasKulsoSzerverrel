package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.ContactPoint;
import com.standalone.standalone.Repository.ContactPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPointServiceImp implements ContactPointService {

    private final ContactPointRepository contactPointRepository;

    public ContactPointServiceImp(ContactPointRepository contactPointRepository) {
        this.contactPointRepository = contactPointRepository;
    }

    @Override
    public ContactPoint save(ContactPoint contactPoint) {
        return contactPointRepository.save(contactPoint);
    }


}
