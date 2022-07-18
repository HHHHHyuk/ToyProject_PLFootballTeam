package study.jpaProject.web.dto.teamrank;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpaProject.domain.team.Team;
import study.jpaProject.domain.team.TeamRank;

import static study.jpaProject.domain.team.QTeam.team;

@Getter
@NoArgsConstructor
public class TeamRankSaveRequestDto {
    private Long id;
    private Long teamId;
    private String season;
    private int victory;
    private int draw;
    private int lose;
    private int score;
    private int loss;

    @Builder
    public TeamRankSaveRequestDto(Long id, Long teamId, String season, int victory, int draw, int lose, int score, int loss) {
        this.id = id;
        this.teamId = teamId;
        this.season = season;
        this.victory = victory;
        this.draw = draw;
        this.lose = lose;
        this.score = score;
        this.loss = loss;
    }

    public TeamRank toEntity(Team team){
        return TeamRank.builder()
                .id(id)
                .team(team)
                .season(season)
                .victory(victory)
                .draw(draw)
                .lose(lose)
                .score(score)
                .loss(loss)
                .build();
    }

}
