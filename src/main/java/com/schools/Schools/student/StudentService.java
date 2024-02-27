package com.schools.Schools.student;

import com.schools.Schools.course.Course;
import com.schools.Schools.course.CourseRepository;
import com.schools.Schools.exceptions.CourseNotFoundException;
import com.schools.Schools.exceptions.ForbiddenActionsException;
import com.schools.Schools.exceptions.InstitutionDoesNotExistException;
import com.schools.Schools.exceptions.StudentNotFoundException;
import com.schools.Schools.institution.Institution;
import com.schools.Schools.institution.InstitutionRepository;
import com.schools.Schools.student.request.AddStudentRequest;
import com.schools.Schools.student.request.ChangeCourseRequest;
import com.schools.Schools.student.request.EditNameRequest;
import com.schools.Schools.student.request.TransferStudentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final InstitutionRepository institutionRepository;

    public Student addStudent(AddStudentRequest request){
        //check if the course exists in that institution
        Course studentCourse = courseRepository.findByIdAndInstitutionId(request.getCourseId(), request.getInstitutionId()).orElseThrow(
                ()-> new CourseNotFoundException("Course not found in the institution")
        );
        //get the institution
        Institution studentInst = institutionRepository.findById(request.getInstitutionId()).orElseThrow(
                ()->new InstitutionDoesNotExistException("Institution does not exist")
        );

        //add a student to the institution
        Student newStudent= Student.builder()
                .name(request.getName())
                .institution(studentInst)
                .course(studentCourse)
                .build();
        return studentRepository.save(newStudent);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Student editName(EditNameRequest request){
        //check if the student exists
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(
                ()->new StudentNotFoundException("Student Not Found")
        );

        student.setName(request.getNewName());

        return studentRepository.save(student);
    }

    public void changeStudentCourse(ChangeCourseRequest request){
        //check if the student is in that institution
        Student student = studentRepository.findByIdAndInstitutionId(request.getStudentId(), request.getInstitutionId()).orElseThrow(
                ()->new StudentNotFoundException("Student not in this institution")
        );

        //check if the course is in that institution
        Course course = courseRepository.findByIdAndInstitutionId(request.getCourseId(), request.getInstitutionId()).orElseThrow(
                ()-> new CourseNotFoundException("Course does not exist in the institution")
        );

        //edit the student course
        student.setCourse(course);
        studentRepository.save(student);
    }

    public void transferStudent(TransferStudentRequest request){
        //check if student already exists in the institution
        Optional<Student> studentCheck = studentRepository.findByIdAndInstitutionId(request.getStudentId(), request.getInstitutionId());
        if (studentCheck.isPresent()){
            throw new ForbiddenActionsException("Student already in the institution");
        }

        //check if the institution exists
        Institution institution = institutionRepository.findById(request.getInstitutionId()).orElseThrow(
                ()->new InstitutionDoesNotExistException("Institution does not exist")
        );

        //check if the student exist
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(
                ()->new StudentNotFoundException("Student not found")
        );

        //check if the course exists in the institution
        Course course = courseRepository.findByIdAndInstitutionId(request.getCourseId(), request.getInstitutionId()).orElseThrow(
                ()->new CourseNotFoundException("Course not found")
        );

        //transfer
        student.setInstitution(institution);
        studentRepository.save(student);
    }

    public List<Student> getInstitutionStudents(Long institutionId){
        return studentRepository.findByInstitutionId(institutionId);
    }

    public List<Student> getCourseStudents(Long courseId){
        return studentRepository.findByCourseId(courseId);
    }
}
