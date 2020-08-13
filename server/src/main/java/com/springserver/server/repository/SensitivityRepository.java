package com.springserver.server.repository;

import com.springserver.server.model.Sensitivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensitivityRepository extends JpaRepository<Sensitivity, Long> {

    List<Sensitivity> findAll();


}

