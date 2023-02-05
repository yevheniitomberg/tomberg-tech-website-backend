package tomberg.tech.websitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomberg.tech.websitebackend.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
}