package com.schools.Schools.student.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddStudentRequest {
    private String name;
    private Long courseId;
    private Long institutionId;

}
