package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.jpaProject.domain.team.Team;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.team.TeamListResponseDto;
import study.jpaProject.web.dto.team.TeamResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TeamService teamService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/team/settings/list")
    public String settingsList(){
        return "team/settings/list";
    }

    @RequestMapping("/team/settings/save")
    public String settingsSave(){
        return "team/settings/save";
    }

    @RequestMapping("/team/settings/update/{id}")
    public String settingsSave(
            @PathVariable Long id,
            Model model
    ){
        TeamResponseDto teamResponseDto = teamService.findById(id);
        model.addAttribute("team", teamResponseDto);
        return "team/settings/update";
    }

    @RequestMapping("/team/settings/view/{id}")
    public String settingView(
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

}
