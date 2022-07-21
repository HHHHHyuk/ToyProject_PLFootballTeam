package study.jpaProject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.team.Team;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;

import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired TeamService teamService;
    @Autowired TeamRepository teamRepository;

    @Test
    void save_테스트() {
        //given
        TeamSaveRequestDto requestDto = new TeamSaveRequestDto("테스트팀", "테스트지역", "테스트경기장", "테스트감독",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일");

        //when
        Long savedId = teamService.save(requestDto);

        //then
        Assertions.assertThat(savedId).isEqualTo(1);
    }

    @Test
    void update_테스트() {
        //given
        TeamSaveRequestDto requestDto = new TeamSaveRequestDto("테스트팀", "테스트지역", "테스트경기장", "테스트감독",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일");
        Long savedId = teamService.save(requestDto);



        TeamUpdateRequestDto updateRequestDto = new TeamUpdateRequestDto("테스트팀1", "테스트지역1", "테스트경기장1", "테스트감독1",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일", null);

        //when
        teamService.update(savedId, updateRequestDto);


        //then
        Team team = teamRepository.findById(savedId).get();
        Assertions.assertThat(team.getTeamName()).isEqualTo(updateRequestDto.getTeamName());
        Assertions.assertThat(team.getTeamArea()).isEqualTo(updateRequestDto.getTeamArea());
    }

    @Test
    void findById_테스트() {
        //given
        TeamSaveRequestDto requestDto = new TeamSaveRequestDto("테스트팀", "테스트지역", "테스트경기장", "테스트감독",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일");
        Long savedId = teamService.save(requestDto);

        //when
        TeamResponseDto responseDto= teamService.findById(savedId);

        //then
        assertThat(requestDto.getTeamName()).isEqualTo(responseDto.getTeamName());
    }

    @Test
    void findAllDesc_테스트() {
        //given
        TeamSaveRequestDto requestDto = new TeamSaveRequestDto("테스트팀", "테스트지역", "테스트경기장", "테스트감독",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일");
        Long savedId = teamService.save(requestDto);

        //when
        List<TeamListResponseDto> all = teamService.findAllDesc();

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getId()).isEqualTo(1);
        assertThat(all.get(0).getTeamName()).isEqualTo("테스트팀");
    }

    @Test
    void delete_테스트() {
        //given
        TeamSaveRequestDto requestDto = new TeamSaveRequestDto("테스트팀", "테스트지역", "테스트경기장", "테스트감독",
                "20220630","테스트원본파일", "테스트저장파일", "테스트임시파일");
        Long savedId = teamService.save(requestDto);

        //when
        teamService.delete(savedId);
        List<TeamListResponseDto> all = teamService.findAllDesc();

        //then
        assertThat(all.size()).isEqualTo(0);
    }
}