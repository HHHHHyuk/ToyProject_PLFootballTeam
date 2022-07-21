package study.jpaProject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.player.QPlayer;
import study.jpaProject.domain.team.QTeam;
import study.jpaProject.web.dto.player.PlayerListResponseDto;
import study.jpaProject.web.dto.player.PlayerSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static study.jpaProject.domain.player.QPlayer.*;
import static study.jpaProject.domain.team.QTeam.*;

@Repository
public class PlayerQueryRepository {

    private final JPAQueryFactory queryFactory;
    public PlayerQueryRepository(EntityManager em){ this.queryFactory = new JPAQueryFactory(em);}

    public Page<PlayerListResponseDto> findByTeamId(PlayerSearchCondition condition, Pageable pageable){
        List<Player> query = queryFactory
                .select(player)
                .from(player)
                .join(player.team, team)
                .fetchJoin()
                .where(
                        teamIdEq(condition.getTeamId())
                )
                .orderBy(player.playerName.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Player> countQuery = queryFactory
                .selectFrom(player)
                .where(teamIdEq(condition.getTeamId()));

        List<PlayerListResponseDto> content = query.stream().map(PlayerListResponseDto::new).collect(Collectors.toList());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression teamIdEq(Long id) {
        return id!=null && id>0L ? player.team.id.eq(id) : null;
    }
}
