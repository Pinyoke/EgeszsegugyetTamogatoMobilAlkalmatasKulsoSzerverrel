package com.springserver.server.repository;

import com.springserver.server.model.ContactPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactPointRepository extends JpaRepository<ContactPoint, Long> {

    List<ContactPoint> findAll();
}
