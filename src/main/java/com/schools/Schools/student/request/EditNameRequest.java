package com.schools.Schools.student.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditNameRequest {
    private String newName;
    private Long studentId;
}
