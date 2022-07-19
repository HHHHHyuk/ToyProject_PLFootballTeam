package study.jpaProject.web.dto.teamrank;

import lombok.Getter;
import study.jpaProject.domain.team.Team;
import study.jpaProject.domain.team.TeamRank;

@Getter
public class TeamRankListResponseDto {
    private String teamName;
    private String imgPath;
    private int gameCount;
    private int victoryPoint;
    private int victory;
    private int draw;
    private int lose;
    private int score;
    private int loss;
    private int scoreAndLoss;

    public TeamRankListResponseDto(TeamRank entity) {
        if(entity!=null){
            this.imgPath = "/webapp/images/team/"+entity.getTeam().getSaveFileName();
            this.teamName = entity.getTeam().getTeamName();
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
