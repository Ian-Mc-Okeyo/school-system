package com.schools.Schools.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdAndInstitutionId(Long studentId, Long instId);
    List<Student> findByInstitutionId(Long institutionId);
    List<Student> findByCourseId(Long courseId);
}
