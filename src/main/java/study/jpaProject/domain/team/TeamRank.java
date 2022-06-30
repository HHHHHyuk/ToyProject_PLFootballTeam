package study.jpaProject.domain.team;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jpaProject.domain.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
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

}
