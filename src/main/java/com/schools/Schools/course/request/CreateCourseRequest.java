package com.schools.Schools.course.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCourseRequest {
    private String name;
    private Long institutionId;
}
