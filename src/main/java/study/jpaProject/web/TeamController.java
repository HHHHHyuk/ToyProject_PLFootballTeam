package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.jpaProject.domain.team.Team;
import study.jpaProject.service.TeamRankService;
import study.jpaProject.service.TeamService;
import study.jpaProject.utils.ApiUtils;
import study.jpaProject.utils.ApiUtils.ApiResult;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamSaveRequestDto;
import study.jpaProject.web.dto.team.TeamUpdateRequestDto;
import study.jpaProject.web.dto.teamrank.TeamRankListResponseDto;
import study.jpaProject.web.dto.teamrank.TeamRankResponseDto;
import study.jpaProject.web.dto.teamrank.TeamRankSaveRequestDto;
import study.jpaProject.web.dto.teamrank.TeamRankSearchCondition;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamRankService teamRankService;

    @PostMapping("/api/v1/team")
    public Long save(@RequestBody TeamSaveRequestDto requestDto){
       return teamService.save(requestDto);
    }

    @PutMapping("/api/v1/team/{id}")
    public Long update(@PathVariable Long id, @RequestBody TeamUpdateRequestDto requestDto) { return teamService.update(id, requestDto); }

    @DeleteMapping("/api/v1/team/{id}")
    public Long delete(@PathVariable Long id) {
        teamService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/team")
    public ApiResult<Page<TeamListResponseDto>> list(Pageable pageable){
        Page<TeamListResponseDto> list = teamService.findAllDesc(pageable);
        return ApiUtils.succes(list);
    }

    @GetMapping("/api/v1/team/rank/{teamId}")
    public ApiResult<TeamRankResponseDto> teamRank(@PathVariable("teamId") Long teamId, String season){
        TeamRankResponseDto responseDto = teamRankService.findBySeasonAndTeamId(season, teamId);
        return ApiUtils.succes(responseDto);
    }

    @PostMapping("/api/v1/team/rank")
    public Long saveAndUpdateRank(@RequestBody TeamRankSaveRequestDto requestDto) { return teamRankService.save(requestDto);}

    @DeleteMapping("/api/v1/teams/rank/{id}")
    public Long deleteRank(@PathVariable("id") Long id){
        teamRankService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/team/rank")
    public ApiResult<List<TeamRankListResponseDto>> teamRankList(TeamRankSearchCondition condition){
        return ApiUtils.succes(teamRankService.getTeamRankList(condition));
    }





}
