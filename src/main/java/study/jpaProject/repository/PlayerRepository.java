package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamAndBackNumber(Team team, @Param("backNumber") Integer backNumber);
    List<Player> findByTeam(Team team);

    @Query("SELECT p FROM Player  p join fetch p.team WHERE p.id =: id")
    Optional<Player> findPlayerJoinTeam(@Param("id") Long id);
}
