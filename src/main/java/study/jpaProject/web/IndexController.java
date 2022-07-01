package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.jpaProject.service.TeamService;
import study.jpaProject.web.dto.team.TeamListResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TeamService teamService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/team/rank")
    public String teanRank(Model model){
        List<TeamListResponseDto> list = teamService.findAllDesc();
        model.addAttribute("list", list);
        return "teamRank";
    }

}
