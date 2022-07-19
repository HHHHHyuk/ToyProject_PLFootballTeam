package study.jpaProject.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.jpaProject.domain.team.QTeam;
import study.jpaProject.domain.team.QTeamRank;
import study.jpaProject.domain.team.TeamRank;
import study.jpaProject.web.dto.teamrank.TeamRankSearchCondition;

import javax.persistence.EntityManager;

import java.util.List;

import static study.jpaProject.domain.team.QTeam.team;
import static study.jpaProject.domain.team.QTeamRank.teamRank;

@Repository
public class TeamRankQueryRepository {

    private final JPAQueryFactory queryFactory;
    public TeamRankQueryRepository(EntityManager em){ this.queryFactory = new JPAQueryFactory(em);}

    public List<TeamRank> rankList(TeamRankSearchCondition condition){
        return queryFactory
                .select(teamRank)
                .from(teamRank)
                .join(teamRank.team, team)
                .fetchJoin()
                .where(
                        seasonEq(condition.getSeason())
                )
                .fetch();
    }

    private BooleanExpression seasonEq(String season) {
        return StringUtils.hasText(season) ? teamRank.season.eq(season) : null;
    }

}
