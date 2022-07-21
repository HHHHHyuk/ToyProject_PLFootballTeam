package study.jpaProject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;
import study.jpaProject.error.CustomException;
import study.jpaProject.repository.PlayerRepository;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.web.dto.player.PlayerListResponseDto;
import study.jpaProject.web.dto.player.PlayerSaveRequestDto;
import study.jpaProject.web.dto.player.PlayerSearchCondition;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlayerServiceTest {

    @Autowired TeamRepository teamRepository;
    @Autowired PlayerRepository playerRepository;
    @Autowired PlayerService playerService;

    @Test
    public void 선수등록() throws Exception {
        //given
        PlayerSaveRequestDto saveRequestDto1 = new PlayerSaveRequestDto(1L, "김민혁", null, null, null, null, 1, null, null, null,null);

        //when
        Long savedId1 = playerService.save(saveRequestDto1);
        Player player = playerRepository.findById(savedId1).get();

        //then
        assertThat(player.getPlayerName()).isEqualTo("김민혁");
    }

    @Test
    public void 선수등록_같은팀_중복등번호방지() {
        //given
        PlayerSaveRequestDto saveRequestDto1 = new PlayerSaveRequestDto(1L, "김민혁", null, null, null, null, 1, null, null, null,null);
        PlayerSaveRequestDto saveRequestDto2 = new PlayerSaveRequestDto(1L, "김민혁", null, null, null, null, 1, null, null, null,null);

        //when
        Long savedId1 = playerService.save(saveRequestDto1);

        //then
        assertThatThrownBy(()->{
            playerService.save(saveRequestDto2);
        }).isInstanceOf(CustomException.class);
    }

    @Test
    public void 선수등록_팀필수선택() {
        //given
        PlayerSaveRequestDto saveRequestDto = new PlayerSaveRequestDto(0L, "김민혁", null, null, null, null, 1, null, null, null, null);
        //when
        //then
        assertThatThrownBy(()->{
            playerService.save(saveRequestDto);
        }).isInstanceOf(Exception.class).hasMessage("소속되는 팀이 없습니다.");
    }

    @Test
    public void 리스트_가져오기() throws Exception {
        //given
        PlayerSaveRequestDto saveRequestDto1 = new PlayerSaveRequestDto(1L, "김민혁1", null, null, null, null, 1, null, null, null,null);
        PlayerSaveRequestDto saveRequestDto2 = new PlayerSaveRequestDto(1L, "김민혁2", null, null, null, null, 2, null, null, null,null);

        //when
        Long savedId1 = playerService.save(saveRequestDto1);
        Long savedId2 = playerService.save(saveRequestDto2);

        PlayerSearchCondition condition = new PlayerSearchCondition();
        condition.setTeamId(1L);
        Page<PlayerListResponseDto> list = playerService.findAllByPlayerNameAsc(condition, getPageable());

        //then
        assertThat(list.getContent().size()).isEqualTo(2);
    }

    public Pageable getPageable(){
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 10;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        return pageable;
    }
}