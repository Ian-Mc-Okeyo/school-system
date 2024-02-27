package com.schools.Schools.institution;

import com.schools.Schools.course.Course;
import com.schools.Schools.course.CourseRepository;
import com.schools.Schools.exceptions.ForbiddenActionsException;
import com.schools.Schools.exceptions.InstitutionDoesNotExistException;
import com.schools.Schools.exceptions.InstitutionNameExistsException;
import com.schools.Schools.institution.request.CreateInstitutionRequest;
import com.schools.Schools.institution.request.UpdateInstitutionRequest;
import com.schools.Schools.institution.response.InstitutionResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final CourseRepository courseRepository;

    public InstitutionResponse createInstitution(CreateInstitutionRequest request){
        Institution newInstitution = Institution.builder()
                .name(request.getName())
                .build();
        try {
            Institution institution = institutionRepository.save(newInstitution);
            return InstitutionResponse.builder()
                    .id(institution.getId())
                    .name(institution.getName())
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new InstitutionNameExistsException("Institution name already exists");
        }
    }

    public List<Institution> getAllInstitutions(){
        return institutionRepository.findAll();
    }

    public InstitutionResponse getByName(String name){
        Optional<InstitutionResponse> inst = institutionRepository.findByName(name);
        if(inst.isEmpty()){
            throw new InstitutionDoesNotExistException("Institution Not Found");
        }
        return inst.get();
    }

    public List<InstitutionResponse> findAllAscending(){
        return institutionRepository.findByOrderByNameAsc();
    }

    public List<InstitutionResponse> findAllDescending(){
        return institutionRepository.findByOrderByNameDesc();
    }

    public void deleteInstitution(Long id){
        //check if the institution has courses
        List<Course> instCourses = courseRepository.findByInstitutionId(id);
        if(!instCourses.isEmpty()){
            //throw forbidden exception
            throw new ForbiddenActionsException("The institution has courses");
        }
        institutionRepository.deleteById(id);
    }

    public void changeInstitutionName(UpdateInstitutionRequest request){
        Optional<Institution> inst = institutionRepository.findById(request.getId());
        if(inst.isEmpty()){
            throw new InstitutionDoesNotExistException("Institution does not exist");
        }
        //check if an institution with that name exists
        Optional<InstitutionResponse> checkedInst  = institutionRepository.findByName(request.getNewName());
        if(checkedInst.isPresent() && !checkedInst.get().getId().equals(inst.get().getId())){
            throw new InstitutionNameExistsException("Institution name already exist");
        }

        inst.get().setName(request.getNewName());
        institutionRepository.save(inst.get());
    }
}
