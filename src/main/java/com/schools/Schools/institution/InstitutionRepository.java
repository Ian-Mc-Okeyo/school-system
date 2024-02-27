package com.schools.Schools.institution;

import com.schools.Schools.course.Course;
import com.schools.Schools.institution.response.InstitutionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<InstitutionResponse> findByName(String name);

    List<InstitutionResponse> findByOrderByNameAsc();
    List<InstitutionResponse> findByOrderByNameDesc();
}
