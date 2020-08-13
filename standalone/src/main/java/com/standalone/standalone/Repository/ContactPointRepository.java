package com.standalone.standalone.Repository;


import com.standalone.standalone.Entity.ContactPoint;
import org.springframework.data.repository.CrudRepository;


public interface ContactPointRepository extends CrudRepository<ContactPoint,Long> {


}
