package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {

    @Query(value = "select max(s.score) from Submission s " +
            "WHERE s.id.challenge.id = :challengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "select s from Submission s " +
            "INNER JOIN Challenge c ON c.id = s.id.challenge.id " +
            "INNER JOIN Acceleration a ON a.challenge.id = c.id " +
            "WHERE c.id = :challengeId AND a.id = :accelerationId")
    List<Submission> findByChallegeIdAndAccelerationId(@Param("challengeId") Long challengeId,@Param("accelerationId") Long accelerationId);

}
