package com.schools.Schools.course;

import com.schools.Schools.course.request.CreateCourseRequest;
import com.schools.Schools.course.request.UpdateCourseRequest;
import com.schools.Schools.exceptions.CourseExistsException;
import com.schools.Schools.exceptions.CourseNotFoundException;
import com.schools.Schools.exceptions.ForbiddenActionsException;
import com.schools.Schools.exceptions.InstitutionDoesNotExistException;
import com.schools.Schools.institution.Institution;
import com.schools.Schools.institution.InstitutionRepository;
import com.schools.Schools.student.Student;
import com.schools.Schools.student.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;
    private final StudentRepository studentRepository;

    public Course createCourse(CreateCourseRequest request){
        System.out.println(request.getInstitutionId());
        System.out.println(request.getName());
        //check if the course exists in the school
        Optional<Course> checkedCourse = courseRepository.findByNameAndInstitutionId(request.getName(), request.getInstitutionId());
        if(checkedCourse.isPresent()){
            throw new CourseExistsException("This course exists in this institution");
        }

        //get the institution and check if it exists
        Optional<Institution> courseInstitute = institutionRepository.findById(request.getInstitutionId());
        if(courseInstitute.isEmpty()){
            throw new InstitutionDoesNotExistException("Institution does not exist");
        }
        Course newCourse = Course.builder()
                .name(request.getName())
                .institution(courseInstitute.get())
                .build();
        return courseRepository.save(newCourse);
    }

    public List<Course> findInstCourses(Long instId){
        return courseRepository.findByInstitutionId(instId);
    }

    public Course searchByNameAndInst(String name, Long id){
        Optional<Course> course = courseRepository.findByNameAndInstitutionId(name, id);
        if(course.isEmpty()){
            throw new CourseNotFoundException("Course not found");
        }

        return course.get();
    }

    public List<Course> findInstCoursesOrderAsc(Long id){
        return courseRepository.findByInstitutionIdOrderByNameAsc(id);
    }

    public List<Course> findInstCoursesOrderDesc(Long id){
        return courseRepository.findByInstitutionIdOrderByNameDesc(id);
    }

    public void deleteCourse(Long id){
        //check if the course has students
        List<Student> students = studentRepository.findByCourseId(id);
        if(!students.isEmpty()){
            throw new ForbiddenActionsException("Course has Students");
        }
        courseRepository.deleteById(id);
    }

    public void editCourseName(UpdateCourseRequest request){
        //check if that course exists in that institution
        if(courseRepository.findByIdAndInstitutionId(request.getCourseId(), request.getInstId()).isEmpty()){
            throw new CourseNotFoundException("Course not found in the institution");
        }
        //check if a course with that name exists in the inst

        Optional<Course> checkCourse = courseRepository.findByNameAndInstitutionId(request.getNewName(), request.getInstId());
        Optional<Course> courseToBeEdited = courseRepository.findById(request.getCourseId());
        if(checkCourse.isPresent() ){
                throw new CourseExistsException("A course with that name exists");
        }

        //edit the course
        courseToBeEdited.get().setName(request.getNewName());
        courseRepository.save(courseToBeEdited.get());
    }
}
