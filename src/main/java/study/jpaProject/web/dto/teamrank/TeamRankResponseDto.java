package study.jpaProject.web.dto.teamrank;

import lombok.Getter;
import study.jpaProject.domain.team.Team;
import study.jpaProject.domain.team.TeamRank;

import java.time.LocalDateTime;

@Getter
public class TeamRankResponseDto {
    private Long id;
    private String season;
    private int gameCount;
    private int victoryPoint;
    private int victory;
    private int draw;
    private int lose;
    private int score;
    private int loss;
    private int scoreAndLoss;;

    public TeamRankResponseDto(TeamRank entity) {
        if(entity!=null){
            this.id = entity.getId();
            this.season = entity.getSeason();
            this.gameCount = entity.getGameCount();
            this.victoryPoint = entity.getVictoryPoint();
            this.victory = entity.getVictory();
            this.draw = entity.getDraw();
            this.lose = entity.getLose();
            this.score = entity.getScore();
            this.loss = entity.getLoss();
            this.scoreAndLoss = entity.getScoreAndLoss();
        }
    }
}
