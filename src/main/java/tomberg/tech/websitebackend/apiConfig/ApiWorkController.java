package tomberg.tech.websitebackend.apiConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomberg.tech.websitebackend.entity.Work;
import tomberg.tech.websitebackend.repository.WorkRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/works")
@RequiredArgsConstructor
public class ApiWorkController {
    private final WorkRepository repository;

    @GetMapping
    public List<Work> getWorks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Work getWork(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Work> createWork(@RequestBody Work work) throws URISyntaxException {
        Work savedWork = repository.save(work);
        return ResponseEntity.created(new URI("/works/" + savedWork.getId())).body(savedWork);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work work) {
        Work currentWork = repository.findById(id).orElseThrow(RuntimeException::new);
        currentWork.setName(work.getName());
        currentWork.setLink(work.getLink());
        currentWork.setDescription(work.getDescription());
        currentWork.setGitHubLink(work.getGitHubLink());
        return ResponseEntity.ok(currentWork);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Work> deleteWork(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
