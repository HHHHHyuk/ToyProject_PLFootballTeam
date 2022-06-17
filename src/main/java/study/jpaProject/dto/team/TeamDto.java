package study.jpaProject.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.jpaProject.entity.Player;
import study.jpaProject.entity.TeamRank;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamDto {
    private Long id;
    private List<Player> players;
    private List<TeamRank> teamRanks;
    private String teamName;
    private String teamArea;
    private String stadium;
    private String manager;
    private String foundingDate;
    private String originalFileName;
    private String saveFileName;

}
