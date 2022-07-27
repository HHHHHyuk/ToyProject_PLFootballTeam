package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.player.Player;
import study.jpaProject.error.CustomException;
import study.jpaProject.repository.PlayerRankQueryRepository;
import study.jpaProject.repository.PlayerRankRepository;
import study.jpaProject.repository.PlayerRepository;
import study.jpaProject.web.dto.playerrank.PlayerRankResponseDto;
import study.jpaProject.web.dto.playerrank.PlayerRankSaveRequestDto;
import study.jpaProject.web.dto.teamrank.TeamRankResponseDto;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerRankService {

    private final PlayerRankRepository playerRankRepository;
    private final PlayerRankQueryRepository playerRankQueryRepository;
    private final PlayerRepository playerRepository;
    private final TeamRankService teamRankService;

    @Transactional
    public Long save(PlayerRankSaveRequestDto requestDto){
        Player player = playerRepository.findPlayerJoinTeam(requestDto.getPlayerId()).orElseThrow(()-> new CustomException("해당 아이디를 가진 선수가 존재하지 않습니다."));
        TeamRankResponseDto bySeasonTeam = teamRankService.findBySeasonAndTeamId(requestDto.getSeason(), player.getTeam().getId());
        if(bySeasonTeam==null) throw new CustomException("선수가 소속된 팀은 해당 시즌을 치르지 않습니다. <br/>선수의 시즌 데이터 등록이 불가능합니다.");

        return playerRankRepository.save(requestDto.toEntity(player)).getId();
    }

    public PlayerRankResponseDto findBySeasonAndPlayerId(String season, Long playerId){
        return null;
    }



}
