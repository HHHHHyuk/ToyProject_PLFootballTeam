package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;
import study.jpaProject.error.CustomException;
import study.jpaProject.repository.PlayerRepository;
import study.jpaProject.repository.TeamQueryRepository;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.utils.FileUtils;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly=true)
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamQueryRepository teamQueryRepository;
    private final PlayerRepository playerRepository;

    private final HttpServletRequest request;

    @Value("${spring.servlet.multipart.location}")
    private  String uploadPath;

    private final String imgPath = "images/";

    @Transactional
    public Long save(TeamSaveRequestDto requestDto){
        boolean empty = teamRepository.findByTeamName(requestDto.getTeamName()).isEmpty();
        if(!empty) { throw new CustomException("이미 존재하는 팀 이름입니다."); }

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            FileUtils.move(request, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"team", requestDto.getSaveFileName());
        }

        return teamRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, TeamUpdateRequestDto requestDto){
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            if(team.getSaveFileName()!=null && !"".equals(team.getSaveFileName())){
                FileUtils.delete(request, imgPath+"team/"+team.getSaveFileName());
            }
            FileUtils.move(request, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"team", requestDto.getSaveFileName());
        } else if ("Y".equals(requestDto.getDeleteYn())) {
            if(FileUtils.delete(request, imgPath+"team/"+team.getSaveFileName())){
                requestDto.saveFileDelete();
            }
        }

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

    public Page<TeamListResponseDto> findAllDesc(Pageable pageable){
        return teamQueryRepository.findTeamListPageAllDesc(pageable);
    }

    public List<TeamListResponseDto> findAllDesc(){
        return teamRepository.findAllDesc().stream().map(TeamListResponseDto::new).collect(Collectors.toList());
    }

    public List<TeamListResponseDto> findAllTeamNameAsc(){
        return teamRepository.findAllByOrderByTeamNameAsc().stream().map(TeamListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));
        if(!playerRepository.findByTeam(team).isEmpty()) throw new CustomException("현재 팀에 소속된 선수가 있습니다. 팀을 삭제할 수 없습니다.");
        if(team.getSaveFileName()!=null && !"".equals(team.getSaveFileName())){
            FileUtils.delete(request, imgPath+"team/"+ team.getSaveFileName());
        }
        teamRepository.delete(team);
    }

}
