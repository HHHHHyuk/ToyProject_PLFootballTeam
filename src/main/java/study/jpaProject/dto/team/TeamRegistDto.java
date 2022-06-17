package study.jpaProject.dto.team;

import lombok.Data;

@Data
public class TeamRegistDto {
    private String teamName;
    private String teamArea;
    private String stadium;
    private String manager;
    private String foundingDate;
}
