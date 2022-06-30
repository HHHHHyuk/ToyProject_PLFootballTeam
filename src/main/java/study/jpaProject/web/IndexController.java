package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import study.jpaProject.service.TeamService;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TeamService teamService;

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
