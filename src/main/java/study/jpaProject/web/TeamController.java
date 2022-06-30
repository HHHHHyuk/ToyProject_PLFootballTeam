package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;

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
//    @GetMapping("/api/v1/teams")
//    public Result list(){
//        List<Team> teams = teamService.findAll();
//        List<TeamDto> result = teams.stream()
//                    .map(t -> new TeamDto(t.getId(), t.getPlayers(), t.getTeamRanks(), t.getTeamName(), t.getTeamArea(), t.getStadium(), t.getManager(), t.getFoundingDate(), t.getOriginalFileName(), t.getSaveFileName()))
//                    .collect(Collectors.toList());
//        return new Result(result.size(), result);
//    }




}
