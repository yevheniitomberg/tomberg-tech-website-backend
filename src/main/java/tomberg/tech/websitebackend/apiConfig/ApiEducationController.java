package tomberg.tech.websitebackend.apiConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomberg.tech.websitebackend.entity.Education;
import tomberg.tech.websitebackend.repository.EducationRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/educations")
@RequiredArgsConstructor
public class ApiEducationController {

    private final EducationRepository repository;

    @GetMapping
    public List<Education> getEducations() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Education getEducation(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) throws URISyntaxException {
        Education savedEducation = repository.save(education);
        return ResponseEntity.created(new URI("/educations/" + savedEducation.getId())).body(savedEducation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        Education currentEducation = repository.findById(id).orElseThrow(RuntimeException::new);
        currentEducation.setTypeOfEducation(education.getTypeOfEducation());
        currentEducation.setSpecialization(education.getSpecialization());
        currentEducation.setEndDate(education.getEndDate());
        currentEducation.setStartDate(education.getStartDate());
        currentEducation.setSchoolName(education.getSchoolName());
        currentEducation.setStillStudying(education.isStillStudying());
        currentEducation = repository.save(education);
        return ResponseEntity.ok(currentEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Education> deleteEducation(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
