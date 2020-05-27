package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    @Query(value = "select u from User u " +
            "INNER JOIN Candidate c ON c.id.user.id = u.id " +
            "INNER JOIN Acceleration a ON a.id = c.id.acceleration.id " +
            "WHERE a.name = :accelerationName")
    List<User> findByAccelerationName(@Param("accelerationName") String accelerationName);

    @Query(value = "select u from User u " +
            "INNER JOIN Candidate c ON c.id.user.id = u.id " +
            "INNER JOIN Company cc ON cc.id = c.id.company.id " +
            "WHERE cc.id = :companyId")
    List<User> findByCompanyId(@Param("companyId") Long companyId);
}
