package tomberg.tech.websitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomberg.tech.websitebackend.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}