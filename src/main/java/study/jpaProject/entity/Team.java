package study.jpaProject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter @Setter
public class Team extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name="team_id")
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy="team")
    private List<TeamRank> teamRanks = new ArrayList<>();

    private String teamName;
    private String teamArea;
    private String stadium;
    private String manager;
    private String foundingDate;
    private String originalFileName;
    private String saveFileName;

    public Team(String teamName, String teamArea, String stadium, String manager, String foundingDate, String originalFileName, String saveFileName) {
        this.teamName = teamName;
        this.teamArea = teamArea;
        this.stadium = stadium;
        this.manager = manager;
        this.foundingDate = foundingDate;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
    }
}
