package study.jpaProject.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.jpaProject.domain.player.PlayerRank;
import study.jpaProject.domain.player.QPlayer;
import study.jpaProject.domain.player.QPlayerRank;
import study.jpaProject.domain.team.QTeam;
import study.jpaProject.web.dto.playerrank.PlayerRankListResponseDto;
import study.jpaProject.web.dto.playerrank.PlayerRankSearchCondition;
import study.jpaProject.web.dto.playerrank.QPlayerRankListResponseDto;

import javax.persistence.EntityManager;

import java.util.List;

import static study.jpaProject.domain.player.QPlayer.*;
import static study.jpaProject.domain.player.QPlayerRank.*;
import static study.jpaProject.domain.team.QTeam.*;

@Repository
public class PlayerRankQueryRepository {

    private final JPAQueryFactory queryFactory;
    public PlayerRankQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PlayerRankListResponseDto> rankList(PlayerRankSearchCondition condition){
        return queryFactory
                .select(new QPlayerRankListResponseDto(
                        player.playerName, player.team.teamName, player.team.saveFileName
                        ,playerRank.gameCount,playerRank.score,playerRank.assist,playerRank.attackPoint
                        ,playerRank.shot,playerRank.foul,playerRank.yellowCard,playerRank.redCard
                        ,playerRank.cornerkick,playerRank.pk,playerRank.offside,playerRank.effectiveShot
                ))
                .from(playerRank)
                .join(playerRank.player, player)
                .join(player.team, team)
                .fetchJoin()
                .where(
                        seasonEq(condition.getSeason())
                )
                .orderBy(getOrderBy(condition.getOrderValue()))
                .fetch();
    }

    private BooleanExpression seasonEq(String season){
        return StringUtils.hasText(season) ? playerRank.season.eq(season) : null;
    }

    private OrderSpecifier<Integer>[] getOrderBy(String orderType){
        OrderSpecifier<Integer>[] sortOrders = null;
        if(orderType!=null && !"".equals(orderType)){
            OrderSpecifier<Integer> desc = null;
            switch (orderType){
                case "assist" : desc = playerRank.assist.desc(); break;
                case "attackPoint" : desc = playerRank.attackPoint.desc(); break;
                case "shot" : desc = playerRank.shot.desc(); break;
                case "foul" : desc = playerRank.foul.desc(); break;
                case "yellowCard" : desc = playerRank.yellowCard.desc(); break;
                case "redCard" : desc = playerRank.redCard.desc(); break;
                case "cornerkick" : desc = playerRank.cornerkick.desc(); break;
                case "pk" : desc = playerRank.pk.desc(); break;
                case "offside" : desc = playerRank.offside.desc(); break;
                case "effectiveShot" : desc = playerRank.effectiveShot.desc(); break;
                case "gameCount" : desc = playerRank.gameCount.desc(); break;
            }
            sortOrders = new OrderSpecifier[]{desc, playerRank.score.desc()};
        }else{
            sortOrders = new OrderSpecifier[]{playerRank.score.desc()};
        }
        return sortOrders;
    }
}
