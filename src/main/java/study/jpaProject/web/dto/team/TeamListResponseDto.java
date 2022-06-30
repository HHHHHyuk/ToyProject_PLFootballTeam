package study.jpaProject.web.dto.team;

import lombok.Builder;
import lombok.Getter;
import study.jpaProject.domain.team.Team;

import java.time.LocalDateTime;

@Getter
public class TeamListResponseDto {
    private Long id;
    private String teamName;
    private String teamArea;
    private String stadium;
    private String manager;
    private String foundingDate;
    private String originalFileName;
    private String saveFileName;
    private LocalDateTime lastModifiedDate;

    @Builder
    public TeamListResponseDto(Team entity) {
        this.id = entity.getId();
        this.teamName = entity.getTeamName();
        this.teamArea = entity.getTeamArea();
        this.stadium = entity.getStadium();
        this.manager = entity.getManager();
        this.foundingDate = entity.getFoundingDate();
        this.originalFileName = entity.getOriginalFileName();
        this.saveFileName = entity.getSaveFileName();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
