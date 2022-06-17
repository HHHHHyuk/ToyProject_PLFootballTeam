package study.jpaProject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter @Setter
public class PlayerRank extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="player_rank_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="player_id")
    private Player player;

    private String season;
    private int score;
    private int assist;
    private int shot;
    private int foul;
    private int yellowCard;
    private int radCard;
    private int cornerkick;
    private int effectiveShot;
    private int gameCount;

}
