package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findById(Long Id);

    @Query(value = "select distinct cc from Company cc " +
            "INNER JOIN Candidate c ON cc.id = c.id.company.id " +
            "INNER JOIN Acceleration a ON a.id = c.id.acceleration.id " +
            "WHERE a.id = :accelerationId")
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "select distinct cc from Company cc " +
            "INNER JOIN Candidate c ON c.id.company.id = cc.id " +
            "INNER JOIN User u ON u.id = c.id.user.id " +
            "WHERE u.id = :userId")
    List<Company> findByUserId(@Param("userId") Long userId);

}
