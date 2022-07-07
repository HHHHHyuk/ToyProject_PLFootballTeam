package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.team.Team;
import study.jpaProject.error.DuplicateTeamException;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly=true)
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    /** 팀 등록 ( 팀 이미지 미구현 ) */
    @Transactional
    public Long save(TeamSaveRequestDto requestDto){
        boolean empty = teamRepository.findByTeamName(requestDto.getTeamName()).isEmpty();
        if(!empty) { throw new DuplicateTeamException(""); }
        return teamRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, TeamUpdateRequestDto requestDto){
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));
        team.updateTeam(
                requestDto.getTeamName(), requestDto.getTeamArea(), requestDto.getStadium(), requestDto.getManager()
                , requestDto.getFoundingDate(), requestDto.getOriginalFileName(), requestDto.getSaveFileName()
        );
        return id;
    }

    public TeamResponseDto findById(Long id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));
        return new TeamResponseDto(team);
    }

    public List<TeamListResponseDto> findAllDesc(){
        return teamRepository.findAllDesc().stream()
                .map(TeamListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));
        teamRepository.delete(team);
    }

}
