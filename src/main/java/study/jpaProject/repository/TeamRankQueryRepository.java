package study.jpaProject.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.jpaProject.domain.team.TeamRank;
import study.jpaProject.web.dto.teamrank.TeamRankSearchCondition;

import javax.persistence.EntityManager;

import java.util.List;

import static study.jpaProject.domain.team.QTeam.team;
import static study.jpaProject.domain.team.QTeamRank.teamRank;

@Repository
public class TeamRankQueryRepository {

    private final JPAQueryFactory queryFactory;
    public TeamRankQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<TeamRank> rankList(TeamRankSearchCondition condition){
        return queryFactory
                .select(teamRank)
                .from(teamRank)
                .join(teamRank.team, team)
                .fetchJoin()
                .where(
                        seasonEq(condition.getSeason())
                )
                .orderBy(getOrderBy(condition.getOrderValue()))
                .fetch();
    }

    private BooleanExpression seasonEq(String season) {
        return StringUtils.hasText(season) ? teamRank.season.eq(season) : null;
    }

    private OrderSpecifier<Integer>[] getOrderBy(String orderType){
        OrderSpecifier<Integer>[] sortOrders = null;
        if(orderType!=null && !"".equals(orderType)){
            OrderSpecifier<Integer> desc = null;
            switch (orderType){
                case "victory" : desc = teamRank.victory.desc(); break;
                case "draw" : desc = teamRank.draw.desc(); break;
                case "lose" : desc = teamRank.lose.desc(); break;
                case "score" : desc = teamRank.score.desc(); break;
                case "loss" : desc = teamRank.loss.desc(); break;
                case "scoreAndLoss" : desc = teamRank.scoreAndLoss.desc(); break;
            }
            sortOrders = new OrderSpecifier[]{ desc, teamRank.victoryPoint.desc() };
        }else{
            sortOrders = new OrderSpecifier[]{ teamRank.victoryPoint.desc() };
        }
        return sortOrders;
         
    }

}
