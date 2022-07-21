package study.jpaProject.web.dto.player;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;

@Getter
@NoArgsConstructor
public class PlayerSaveRequestDto {
    private Long teamId;
    private String playerName;
    private String nationality;
    private Integer height;
    private Double weight;
    private String position;
    private Integer backNumber;
    private String originalFileName;
    private String saveFileName;
    private String tempFileName;
    private String deleteYn;

    @Builder
    public PlayerSaveRequestDto(Long teamId, String playerName, String nationality, Integer height, Double weight, String position, Integer backNumber, String originalFileName, String saveFileName, String tempFileName, String deleteYn) {
        this.teamId = teamId;
        this.playerName = playerName;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.backNumber = backNumber;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.tempFileName = tempFileName;
        this.deleteYn = deleteYn;
    }

    public Player toEntity(Team team){
            return Player.builder()
                    .team(team)
                    .playerName(playerName)
                    .nationality(nationality)
                    .height(height)
                    .weight(weight)
                    .position(position)
                    .backNumber(backNumber)
                    .originalFileName(originalFileName)
                    .saveFileName(saveFileName)
                    .build();
        }
}

