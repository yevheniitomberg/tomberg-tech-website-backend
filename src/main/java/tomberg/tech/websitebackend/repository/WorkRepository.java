package tomberg.tech.websitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomberg.tech.websitebackend.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
}