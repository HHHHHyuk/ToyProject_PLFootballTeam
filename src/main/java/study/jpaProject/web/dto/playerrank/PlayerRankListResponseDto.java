package study.jpaProject.web.dto.playerrank;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import study.jpaProject.domain.team.TeamRank;

@Getter
public class PlayerRankListResponseDto {
    private String playerName;
    private String teamName;
    private String teamLogoSaveFileName;
    private int gameCount;
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

    public PlayerRankListResponseDto(){}

    @QueryProjection
    public PlayerRankListResponseDto(String playerName, String teamName, String teamLogoSaveFileName, int gameCount, int score, int assist, int attackPoint, int shot, int foul, int yellowCard, int redCard, int cornerkick, int pk, int offside, int effectiveShot){
        this.playerName=playerName;
        this.teamName=teamName;
        this.teamLogoSaveFileName=teamLogoSaveFileName;
        this.gameCount=gameCount;
        this.score=score;
        this.assist=assist;
        this.attackPoint=attackPoint;
        this.shot=shot;
        this.foul=foul;
        this.yellowCard=yellowCard;
        this.redCard=redCard;
        this.cornerkick=cornerkick;
        this.pk=pk;
        this.offside=offside;
        this.effectiveShot=effectiveShot;
    }

}
