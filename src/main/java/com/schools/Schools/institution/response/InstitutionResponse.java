package com.schools.Schools.institution.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstitutionResponse {
    private Long id;
    private String name;
}
