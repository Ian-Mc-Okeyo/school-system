package com.schools.Schools.student.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeCourseRequest {
    private Long courseId;
    private Long institutionId;
    private Long studentId;
}
