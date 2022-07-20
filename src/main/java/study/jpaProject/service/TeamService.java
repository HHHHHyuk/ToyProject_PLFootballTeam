package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.team.Team;
import study.jpaProject.error.CustomException;
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

    private final HttpServletRequest req;

    @Value("${spring.servlet.multipart.location}")
    private  String uploadPath;

    private final String imgPath = "images/";

    /** 팀 등록 ( 팀 이미지 미구현 ) */
    @Transactional
    public Long save(TeamSaveRequestDto requestDto){
        boolean empty = teamRepository.findByTeamName(requestDto.getTeamName()).isEmpty();
        if(!empty) { throw new CustomException("이미 존재하는 팀 이름입니다."); }

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            FileUtils.move(req, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"team", requestDto.getSaveFileName());
        }

        return teamRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, TeamUpdateRequestDto requestDto){
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            if(team.getSaveFileName()!=null && !"".equals(team.getSaveFileName())){
                FileUtils.delete(req, imgPath+"team/"+team.getSaveFileName());
            }
            FileUtils.move(req, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"team", requestDto.getSaveFileName());
        } else if ("Y".equals(requestDto.getDeleteYn())) {
            if(FileUtils.delete(req, imgPath+"team/"+team.getSaveFileName())){
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

    @Transactional
    public void delete (Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 팀은 존재하지 않습니다. team_id = " + id));
        if(team.getSaveFileName()!=null && !"".equals(team.getSaveFileName())){
            FileUtils.delete(req, imgPath+"team/"+ team.getSaveFileName());
        }
        teamRepository.delete(team);
    }

}
