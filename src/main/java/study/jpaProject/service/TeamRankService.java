package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.team.Team;
import study.jpaProject.domain.team.TeamRank;
import study.jpaProject.error.CustomException;
import study.jpaProject.error.NotFoundException;
import study.jpaProject.repository.TeamRankQueryRepository;
import study.jpaProject.repository.TeamRankRepository;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.web.dto.teamrank.TeamRankListResponseDto;
import study.jpaProject.web.dto.teamrank.TeamRankResponseDto;
import study.jpaProject.web.dto.teamrank.TeamRankSaveRequestDto;
import study.jpaProject.web.dto.teamrank.TeamRankSearchCondition;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamRankService {

    private final TeamRankRepository teamRankRepository;
    private final TeamRankQueryRepository teamRankQueryRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Long save(TeamRankSaveRequestDto requestDto){
        List<TeamRank> seasonTeamList = teamRankRepository.findBySeason(requestDto.getSeason());
        if(seasonTeamList!=null && seasonTeamList.size()>=20) throw new CustomException("한 시즌에 20팀을 초과할 수 없습니다.");

        Team team = teamRepository.findById(requestDto.getTeamId()).orElseThrow(() -> new NotFoundException("해당 팀은 존재하지 않습니다."));
        return teamRankRepository.save(requestDto.toEntity(team)).getId();
    }


    public TeamRankResponseDto findBySeasonAndTeamId(String season, Long teamId){
        TeamRank teamRank = teamRankRepository.findBySeasonAndTeamId(season, teamId).orElse(null);
        return new TeamRankResponseDto(teamRank);
    }

    @Transactional
    public void delete(Long id){
        TeamRank teamRank = teamRankRepository.findById(id).orElseThrow(() -> new CustomException("해당 번호의 시즌 기록은 존재하지 않습니다."));
        teamRankRepository.delete(teamRank);
    }

    public List<TeamRankListResponseDto> getTeamRankList(TeamRankSearchCondition condition){
        return teamRankQueryRepository.rankList(condition).stream().map(t -> new TeamRankListResponseDto(t)).collect(Collectors.toList());
    }
}
