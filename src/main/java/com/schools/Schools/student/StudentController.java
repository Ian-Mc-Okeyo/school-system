package com.schools.Schools.student;

import com.schools.Schools.student.request.AddStudentRequest;
import com.schools.Schools.student.request.ChangeCourseRequest;
import com.schools.Schools.student.request.EditNameRequest;
import com.schools.Schools.student.request.TransferStudentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody AddStudentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(request));
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return HttpStatus.OK;
    }

    @PutMapping("/edit-name")
    public ResponseEntity<Student> editName(@RequestBody EditNameRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.editName(request));
    }

    @PutMapping("/edit-course")
    public HttpStatus editCourse(@RequestBody ChangeCourseRequest request){
        studentService.changeStudentCourse(request);
        return HttpStatus.OK;
    }

    @PutMapping("/transfer")
    public HttpStatus transferStudent(@RequestBody TransferStudentRequest request){
        studentService.transferStudent(request);
        return HttpStatus.OK;
    }

    @GetMapping("get-inst-students")
    public ResponseEntity<List<Student>> getInstitutionStudents(@RequestParam Long institutionId){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getInstitutionStudents(institutionId));
    }

    @GetMapping("/course-students")
    public ResponseEntity<List<Student>> getCourseStudents(@RequestParam Long courseId){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getCourseStudents(courseId));
    }
}
