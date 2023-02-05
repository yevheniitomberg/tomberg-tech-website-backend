package tomberg.tech.websitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomberg.tech.websitebackend.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}