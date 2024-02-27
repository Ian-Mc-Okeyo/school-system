package com.schools.Schools.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByNameAndInstitutionId(String name, Long id);
    Optional<Course> findByIdAndInstitutionId(Long courseId, Long instId);

    List<Course> findByInstitutionId(Long id);

    List<Course> findByInstitutionIdOrderByNameAsc(Long id);
    List<Course> findByInstitutionIdOrderByNameDesc(Long id);
}
