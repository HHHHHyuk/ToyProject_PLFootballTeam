package study.jpaProject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.entity.Team;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired TeamService teamService;

    @Test
//    @Commit
    public void 팀등록 () throws Exception {
        //given
        Team team = new Team("맨체스터 유나이티드 FC","노스웨스트 잉글랜드 그레이터 맨체스터 주", "올드 트래퍼드",
                "에릭 텐하흐", "1878년 03월 05일", null, null);
        //when
        Long savedId = teamService.regist(team);
        //then
        assertThat(team.getId()).isEqualTo(savedId);
    }

}