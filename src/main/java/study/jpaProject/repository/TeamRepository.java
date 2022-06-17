package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpaProject.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
