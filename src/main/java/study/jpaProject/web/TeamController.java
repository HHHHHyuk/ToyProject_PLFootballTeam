package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.jpaProject.domain.team.Team;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/v1/teams")
    public Long save(@RequestBody TeamSaveRequestDto requestDto){
       return teamService.save(requestDto);
    }
//
//    @PutMapping("/api/v1/teams")
//    public UpdateResponse
//
    @GetMapping("/api/v1/teams")
    public List<TeamListResponseDto> list(){
        return teamService.findAllDesc();
    }




}
