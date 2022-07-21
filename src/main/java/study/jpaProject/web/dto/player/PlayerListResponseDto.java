package study.jpaProject.web.dto.player;

import lombok.Builder;
import lombok.Getter;
import study.jpaProject.domain.player.Player;

import java.time.LocalDateTime;

@Getter
public class PlayerListResponseDto {
    private Long id;
    private String playerName;
    private String teamName;
    private Integer backNumber;
    private String originalFileName;
    private String saveFileName;
    private String imgPath;
    private LocalDateTime lastModifiedDate;

    @Builder
    public PlayerListResponseDto(Player entity) {
        this.id = entity.getId();
        this.playerName = entity.getPlayerName();
        this.teamName = entity.getTeam().getTeamName();
        this.backNumber = entity.getBackNumber();
        this.originalFileName = entity.getOriginalFileName();
        this.saveFileName = entity.getSaveFileName();
        this.imgPath = "/webapp/images/player/"+entity.getSaveFileName();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
