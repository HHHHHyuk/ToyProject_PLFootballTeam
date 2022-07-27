package study.jpaProject.web.dto.playerrank;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.player.PlayerRank;
import study.jpaProject.domain.team.Team;
import study.jpaProject.domain.team.TeamRank;

@Getter
@NoArgsConstructor
public class PlayerRankSaveRequestDto {
    private Long id;
    private Long playerId;
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
    public PlayerRankSaveRequestDto(Long id, Long playerId, String season, int score, int assist, int attackPoint, int shot, int foul, int yellowCard, int redCard, int cornerkick, int pk, int offside, int effectiveShot, int gameCount) {
        this.id = id;
        this.playerId = playerId;
        this.season = season;
        this.score = score;
        this.assist = assist;
        this.attackPoint = attackPoint;
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

    public PlayerRank toEntity(Player player){
        return PlayerRank.builder()
                .player(player)
                .season(season)
                .score(score)
                .assist(assist)
                .shot(shot)
                .foul(foul)
                .yellowCard(yellowCard)
                .redCard(redCard)
                .cornerkick(cornerkick)
                .pk(pk)
                .offside(offside)
                .effectiveShot(effectiveShot)
                .gameCount(gameCount)
                .build();
    }

}
