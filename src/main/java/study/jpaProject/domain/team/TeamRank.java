package study.jpaProject.domain.team;

import lombok.*;
import study.jpaProject.domain.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TeamRank extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="team_rank_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    private String season;
    private int gameCount;
    private int victoryPoint;
    private int victory;
    private int draw;
    private int lose;
    private int score;
    private int loss;
    private int scoreAndLoss;

    @Builder
    public TeamRank(Long id, Team team, String season, int victory, int draw, int lose, int score, int loss){
        this.id = id;
        this.team = team;
        this.season = season;
        this.gameCount = victory+draw+lose;
        this.victoryPoint = victory*3 + draw ;
        this.victory = victory;
        this.draw = draw;
        this.lose = lose;
        this.score = score;
        this.loss = loss;
        this.scoreAndLoss = score-loss;
    }

    public void updateTeamRank(int victory, int draw, int lose, int score, int loss){
        this.gameCount = victory + draw + lose;
        this.victoryPoint = victory*3 + draw;
        this.victory = victory;
        this.draw = draw;
        this.lose = lose;
        this.score = score;
        this.loss = loss;
        this.scoreAndLoss = score-loss;
    }

}
