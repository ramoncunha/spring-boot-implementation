package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    List<Acceleration> findAll();

    Optional<Acceleration> findByName(String name);

    @Query(value = "select a from Acceleration a " +
            "INNER JOIN Candidate c ON c.id.acceleration.id = a.id " +
            "INNER JOIN Company cc ON cc.id = c.id.company.id " +
            "WHERE cc.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

}
