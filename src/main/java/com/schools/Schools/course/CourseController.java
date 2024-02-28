package com.schools.Schools.course;

import com.schools.Schools.course.request.CreateCourseRequest;
import com.schools.Schools.course.request.UpdateCourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Course>> getInstCourses(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findInstCourses(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Course> searchCourseByName(@RequestParam String name, @RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.searchByNameAndInst(name, id));
    }

    @GetMapping("/get-order-asc")
    public ResponseEntity<List<Course>> findAscOrder(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findInstCoursesOrderAsc(id));
    }

    @GetMapping("/get-order-desc")
    public ResponseEntity<List<Course>> findDescOrder(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findInstCoursesOrderDesc(id));
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteCourse(@RequestParam Long id){
        courseService.deleteCourse(id);
        return HttpStatus.OK;
    }

    @PutMapping("/edit-name")
    public HttpStatus editCourseName(@RequestBody UpdateCourseRequest request){
        courseService.editCourseName(request);
        return HttpStatus.OK;
    }
}
