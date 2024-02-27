package com.schools.Schools.student.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferStudentRequest {
    private Long studentId;
    private Long institutionId;
    private Long courseId;
}
