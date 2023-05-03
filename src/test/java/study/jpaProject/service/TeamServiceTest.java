package study.jpaProject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.team.Team;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;

import javax.persistence.PersistenceContext;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
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

    @Test
    void delete_선수보유시_삭제방지_테스트() {
        //given
        //when

        //then
        assertThatThrownBy(()->{
            teamService.delete(7L);
        }).isInstanceOf(Exception.class).hasMessage("현재 팀에 소속된 선수가 있습니다. 팀을 삭제할 수 없습니다.");
    }

    @Test
    void test() {

        String[][] clothes  = new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        HashMap<String, List<String>> clothesMap = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
            List<String> clothesNames = new ArrayList<>();
            if(clothesMap.get(clothes[i][1]) != null) {
                clothesNames.addAll(clothesMap.get(clothes[i][1]));
            }
            clothesNames.add(clothes[i][0]);
        }

        for(List<String> a : clothesMap.val)

        System.out.println(clothesMap);

    }
}