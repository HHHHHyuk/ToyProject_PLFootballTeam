package study.jpaProject.web.dto.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpaProject.domain.team.Team;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {
    private String teamName;
    private String teamArea;
    private String stadium;
    private String manager;
    private String foundingDate;
    private String originalFileName;
    private String saveFileName;
    private String filePath;
    private String tempFileName;

    @Builder
    public TeamSaveRequestDto(String teamName, String teamArea, String stadium, String manager, String foundingDate, String originalFileName, String saveFileName, String filePath, String tempFileName) {
        this.teamName = teamName;
        this.teamArea = teamArea;
        this.stadium = stadium;
        this.manager = manager;
        this.foundingDate = foundingDate;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.filePath = filePath;
        this.tempFileName = tempFileName;
    }

    public Team toEntity(){
        return Team.builder()
                .teamName(teamName)
                .teamArea(teamArea)
                .stadium(stadium)
                .manager(manager)
                .foundingDate(foundingDate)
                .originalFileName(originalFileName)
                .saveFileName(saveFileName)
                .build();
    }
}
