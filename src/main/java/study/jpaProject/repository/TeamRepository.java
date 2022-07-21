package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.jpaProject.domain.team.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t ORDER BY t.id DESC")
    List<Team> findAllDesc();

    List<Team> findAllByOrderByTeamNameAsc();

    Optional<Team> findByTeamName(@Param("teamName")  String teamName);
}
