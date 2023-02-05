package tomberg.tech.websitebackend.apiConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomberg.tech.websitebackend.entity.Experience;
import tomberg.tech.websitebackend.repository.ExperienceRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/experiences")
@RequiredArgsConstructor
public class ApiExperienceController {
    private final ExperienceRepository repository;

    @GetMapping
    public List<Experience> getExperiences() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Experience getExperience(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) throws URISyntaxException {
        Experience savedExperience = repository.save(experience);
        return ResponseEntity.created(new URI("/experiences/" + savedExperience.getId())).body(savedExperience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience experience) {
        Experience currentExperience = repository.findById(id).orElseThrow(RuntimeException::new);
        currentExperience.setCompanyName(experience.getCompanyName());
        currentExperience.setPosition(experience.getPosition());
        currentExperience.setStillWorking(experience.isStillWorking());
        currentExperience.setEndDate(experience.getEndDate());
        currentExperience.setStartDate(experience.getStartDate());
        currentExperience = repository.save(experience);
        return ResponseEntity.ok(currentExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Experience> deleteExperience(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
