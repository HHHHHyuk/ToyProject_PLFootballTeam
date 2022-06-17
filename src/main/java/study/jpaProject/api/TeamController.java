package study.jpaProject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.jpaProject.dto.team.TeamDto;
import study.jpaProject.dto.team.TeamRegistDto;
import study.jpaProject.entity.Team;
import study.jpaProject.service.TeamService;
import study.jpaProject.util.RegistResponse;
import study.jpaProject.util.Result;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/v1/teams")
    public RegistResponse registTeam(@RequestBody @Validated TeamRegistDto teamRegistDto){
        // teamName,  teamArea,  stadium,  manager,  foundingDate,  originalFileName,  saveFileName
        Team team = new Team(teamRegistDto.getTeamName(), teamRegistDto.getTeamArea(), teamRegistDto.getStadium(), teamRegistDto.getManager(), teamRegistDto.getFoundingDate(),  null, null);

        Long id = teamService.regist(team);
        return new RegistResponse(id);
    }

    @GetMapping("/api/v1/teams")
    public Result list(){
        List<Team> teams = teamService.findAll();
        List<TeamDto> result = teams.stream()
                    .map(t -> new TeamDto(t.getId(), t.getPlayers(), t.getTeamRanks(), t.getTeamName(), t.getTeamArea(), t.getStadium(), t.getManager(), t.getFoundingDate(), t.getOriginalFileName(), t.getSaveFileName()))
                    .collect(Collectors.toList());
        return new Result(result.size(), result);
    }


}
