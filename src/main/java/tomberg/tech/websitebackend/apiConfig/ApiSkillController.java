package tomberg.tech.websitebackend.apiConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomberg.tech.websitebackend.entity.Skill;
import tomberg.tech.websitebackend.repository.SkillRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class ApiSkillController {
    private final SkillRepository repository;

    @GetMapping
    public List<Skill> getSkills() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) throws URISyntaxException {
        Skill savedSkill = repository.save(skill);
        return ResponseEntity.created(new URI("/skills/" + savedSkill.getId())).body(savedSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        Skill currentSkill = repository.findById(id).orElseThrow(RuntimeException::new);
        currentSkill.setName(skill.getName());
        currentSkill.setPercentage(skill.getPercentage());
        currentSkill = repository.save(skill);
        return ResponseEntity.ok(currentSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
