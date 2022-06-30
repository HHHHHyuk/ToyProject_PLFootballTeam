package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.jpaProject.domain.team.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t ORDER BY t.id DESC")
    List<Team> findAllDesc();
}
