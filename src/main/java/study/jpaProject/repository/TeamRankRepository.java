package study.jpaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.jpaProject.domain.team.TeamRank;

import java.util.List;
import java.util.Optional;

public interface TeamRankRepository extends JpaRepository<TeamRank, Long> {

    //@Query("SELECT tr FROM TeamRank tr WHERE tr.season=:season")
    List<TeamRank> findBySeason(@Param("season") String season);

    //@Query("SELECT tr FROM TeamRank tr WHERE tr.season=:season AND tr.teamId=:teamId")
    Optional<TeamRank> findBySeasonAndTeamId(@Param("season")String season, @Param("teamId")Long teamId);
}
