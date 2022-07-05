package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.jpaProject.domain.team.Team;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/v1/teams")
    public Long save(@RequestBody TeamSaveRequestDto requestDto){
       return teamService.save(requestDto);
    }

    @PutMapping("/api/v1/teams/{id}")
    public Long update(@PathVariable Long id, @RequestBody TeamUpdateRequestDto requestDto) { return teamService.update(id, requestDto); }

    @DeleteMapping("/api/v1/teams/{id}")
    public Long delete(@PathVariable Long id) {
        teamService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/teams")
    public List<TeamListResponseDto> list(){
        return teamService.findAllDesc();
    }




}
