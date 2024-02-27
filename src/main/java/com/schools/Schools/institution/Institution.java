package com.schools.Schools.institution;

import com.schools.Schools.course.Course;
import com.schools.Schools.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Institution {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courseList;
}
