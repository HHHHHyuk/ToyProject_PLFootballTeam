package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamAndBackNumber(Team team, @Param("backNumber") Integer backNumber);
}
