package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.dto.team.TeamRegistDto;
import study.jpaProject.entity.Team;
import study.jpaProject.repository.TeamRepository;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    /**
     * 팀 등록 ( 팀 이미지 미구현 )
     */
    @Transactional
    public Long regist(Team team){
        teamRepository.save(team);
        return team.getId();
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

}
