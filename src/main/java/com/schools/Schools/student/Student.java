package com.schools.Schools.student;

import com.schools.Schools.course.Course;
import com.schools.Schools.institution.Institution;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="institution_id")
    private Institution institution;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
}
