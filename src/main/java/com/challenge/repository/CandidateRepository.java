package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Optional<Candidate> findById(CandidateId id);

    @Query(value = "select c from Candidate c " +
            "INNER JOIN Company cc ON cc.id = c.id.company.id " +
            "WHERE cc.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "select c from Candidate c " +
            "INNER JOIN Acceleration a ON a.id = c.id.acceleration.id " +
            "WHERE a.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
