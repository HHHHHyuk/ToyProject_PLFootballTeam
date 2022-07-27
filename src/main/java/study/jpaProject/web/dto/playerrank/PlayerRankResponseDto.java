package study.jpaProject.web.dto.playerrank;

import lombok.Getter;
import study.jpaProject.domain.player.PlayerRank;
import study.jpaProject.domain.team.TeamRank;

@Getter
public class PlayerRankResponseDto {
    private Long id;
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

    public PlayerRankResponseDto(PlayerRank entity) {
        if(entity!=null){
            this.id = entity.getId();
            this.season = entity.getSeason();
            this.assist = entity.getGameCount();
            this.attackPoint = entity.getAttackPoint();
            this.shot = entity.getShot();
            this.foul = entity.getFoul();
            this.yellowCard = entity.getYellowCard();
            this.redCard = entity.getRedCard();
            this.cornerkick = entity.getCornerkick();
            this.pk = entity.getPk();
            this.offside = entity.getOffside();
            this.effectiveShot = entity.getEffectiveShot();
            this.gameCount = entity.getGameCount();
        }
    }
}
