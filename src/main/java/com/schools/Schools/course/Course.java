package com.schools.Schools.course;

import com.schools.Schools.institution.Institution;
import com.schools.Schools.student.Student;
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
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

}
