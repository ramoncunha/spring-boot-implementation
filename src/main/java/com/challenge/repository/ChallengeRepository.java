package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

    @Query(value = "select c from Challenge c " +
            "INNER JOIN Submission s ON s.id.challenge.id = c.id " +
            "INNER JOIN User u ON u.id = s.id.user.id " +
            "INNER JOIN Acceleration a ON c.id = a.challenge.id " +
            "WHERE u.id = :userId AND a.id = :accelerationId")
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,@Param("userId") Long userId);

}
