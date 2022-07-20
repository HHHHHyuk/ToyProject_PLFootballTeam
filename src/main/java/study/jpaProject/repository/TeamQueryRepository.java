package study.jpaProject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study.jpaProject.domain.team.QTeam;
import study.jpaProject.domain.team.Team;
import study.jpaProject.web.dto.team.TeamListResponseDto;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static study.jpaProject.domain.team.QTeam.team;

@Repository
public class TeamQueryRepository {
    private final JPAQueryFactory queryFactory;
    public TeamQueryRepository(EntityManager em){ this.queryFactory = new JPAQueryFactory(em);}

    public Page<TeamListResponseDto> findTeamListPageAllDesc(Pageable pageable){
        QueryResults<Team> results = queryFactory
                .selectFrom(team)
                .orderBy(team.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<TeamListResponseDto> content = results.getResults().stream().map(t->new TeamListResponseDto(t)).collect(Collectors.toList());
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

}
