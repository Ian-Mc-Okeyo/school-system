package com.schools.Schools.institution;

import com.schools.Schools.institution.request.CreateInstitutionRequest;
import com.schools.Schools.institution.request.UpdateInstitutionRequest;
import com.schools.Schools.institution.response.InstitutionResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institution")
@AllArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;
    @PostMapping("/create")
    public ResponseEntity<InstitutionResponse> createInstitution(@RequestBody CreateInstitutionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.createInstitution(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Institution>> getAllInstitutions(){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.getAllInstitutions());
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<InstitutionResponse> getByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.getByName(name));
    }

    @GetMapping("/get-all-ascending")
    public ResponseEntity<List<InstitutionResponse>> getAllAscending(){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.findAllAscending());
    }

    @GetMapping("/get-all-descending")
    public ResponseEntity<List<InstitutionResponse>> getAllDescending(){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.findAllDescending());
    }

    @PutMapping("/change-name")
    public HttpStatus changeName(@RequestBody UpdateInstitutionRequest request){
        institutionService.changeInstitutionName(request);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        return HttpStatus.OK;
    }

}
