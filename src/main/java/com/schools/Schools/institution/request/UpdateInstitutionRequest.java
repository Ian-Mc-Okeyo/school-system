package com.schools.Schools.institution.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateInstitutionRequest {
    private Long id;
    private String newName;
}
