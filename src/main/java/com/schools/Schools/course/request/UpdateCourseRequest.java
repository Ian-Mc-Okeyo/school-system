package com.schools.Schools.course.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCourseRequest {
    private Long courseId;
    private Long instId;
    private String newName;
}
