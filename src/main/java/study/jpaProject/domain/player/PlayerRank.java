package study.jpaProject.domain.player;

import lombok.*;
import study.jpaProject.domain.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class PlayerRank extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="player_rank_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="player_id")
    private Player player;

    private String season;
    private int score;
    private int assist;
    private int attackPoint;
    private int shot;
    private int foul;
    private int yellowCard;
    private int redCard;
    private int cornerkick;
    private int pk;
    private int offside;
    private int effectiveShot;
    private int gameCount;

    @Builder
    public PlayerRank(Player player, String season, int score, int assist, int shot, int foul, int yellowCard, int redCard, int cornerkick, int pk, int offside, int effectiveShot, int gameCount) {
        this.player = player;
        this.season = season;
        this.score = score;
        this.assist = assist;
        this.attackPoint = score+assist;
        this.shot = shot;
        this.foul = foul;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.cornerkick = cornerkick;
        this.pk = pk;
        this.offside = offside;
        this.effectiveShot = effectiveShot;
        this.gameCount = gameCount;
    }

    public void updatePlayerRank(int score, int assist, int shot, int foul, int yellowCard, int redCard, int cornerkick, int pk, int offside, int effectiveShot, int gameCount){
        this.score=score;
        this.assist=assist;
        this.attackPoint = score+assist;
        this.shot=shot;
        this.foul=foul;
        this.yellowCard=yellowCard;
        this.redCard=redCard;
        this.cornerkick=cornerkick;
        this.pk=pk;
        this.offside=offside;
        this.effectiveShot=effectiveShot;
        this.gameCount=gameCount;
    }
}
