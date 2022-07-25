package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import study.jpaProject.domain.player.PlayerRank;

import java.util.List;
import java.util.Optional;

public interface PlayerRankRepository extends JpaRepository<PlayerRank, Long> {

    List<PlayerRank> findBySeason(@Param("season")String season);
    Optional<PlayerRank> findBySeasonAndPlayerId(@Param("season")String season, @Param("playerId")Long playerId);
}
