package study.jpaProject.web.dto.player;

import lombok.Getter;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;

import java.time.LocalDateTime;

@Getter
public class PlayerResponseDto {
    private Long id;
    private Long teamId;
    private String playerName;
    private String teamName;
    private String nationaliy;
    private Integer height;
    private Double weight;
    private Integer backNumber;
    private String position;
    private String originalFileName;
    private String saveFileName;
    private String imgPath;
    private LocalDateTime lastModifiedDate;

    public PlayerResponseDto(Player entity) {
        this.id = entity.getId();
        this.teamId = entity.getTeam().getId();
        this.playerName = entity.getPlayerName();
        this.teamName = entity.getTeam().getTeamName();
        this.nationaliy = entity.getNationality();
        this.height = entity.getHeight();
        this.weight = entity.getWeight();
        this.backNumber = entity.getBackNumber();
        this.position = entity.getPosition();
        this.originalFileName = entity.getOriginalFileName();
        this.saveFileName = entity.getSaveFileName();
        if(entity.getSaveFileName()!=null && !"".equals(entity.getSaveFileName())){
            this.imgPath = "/webapp/images/player/" +entity.getSaveFileName();
        }else{
            this.imgPath = "/webapp/images/empty_player.png";
        }
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
