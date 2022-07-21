package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.jpaProject.domain.team.Team;
import study.jpaProject.service.PlayerService;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.player.PlayerResponseDto;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/team/settings/list")
    public String teamList(){
        return "team/settings/list";
    }

    @RequestMapping("/team/settings/save")
    public String teamSave(){
        return "team/settings/save";
    }

    @RequestMapping("/team/settings/update/{id}")
    public String teamUpdate(
            @PathVariable Long id,
            Model model
    ){
        TeamResponseDto teamResponseDto = teamService.findById(id);
        model.addAttribute("team", teamResponseDto);
        return "team/settings/update";
    }

    @RequestMapping("/team/settings/view/{id}")
    public String teamView(
            @PathVariable Long id,
            Model model
    ){
        TeamResponseDto teamResponseDto = teamService.findById(id);
        model.addAttribute("team", teamResponseDto);
        return "team/settings/view";
    }

    @RequestMapping("/team/settings/rank/save/{id}")
    public String teanRankSave(
            @PathVariable("id") Long id,
            Model model){
        TeamResponseDto team = teamService.findById(id);
        model.addAttribute("team", team);
        return "team/settings/rankSave";
    }

    @RequestMapping("/team/rankList")
    public String teamRankList(){
        return "team/rankList";
    }

    @RequestMapping("/player/settings/list")
    public String playerList(Model model) {
        List<TeamListResponseDto> list = teamService.findAllTeamNameAsc();
        model.addAttribute("teamList", list);
        return "player/settings/list";
    }

    @RequestMapping("/player/settings/save")
    public String playerSave(Model model) {
        List<TeamListResponseDto> list = teamService.findAllTeamNameAsc();
        model.addAttribute("teamList", list);
        return "player/settings/save";
    }

    @RequestMapping("/player/settings/view/{id}")
    public String playerView(@PathVariable("id") Long id, Model model) {
        PlayerResponseDto player = playerService.findById(id);
        model.addAttribute("player", player);
        return "player/settings/view";
    }

    @RequestMapping("/player/settings/update/{id}")
    public String playerUpdate(@PathVariable Long id, Model model ){
        List<TeamListResponseDto> list = teamService.findAllTeamNameAsc();
        model.addAttribute("teamList", list);

        PlayerResponseDto playerResponseDto = playerService.findById(id);
        model.addAttribute("player", playerResponseDto);
        return "player/settings/update";
    }
}
