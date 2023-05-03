package study.jpaProject.domain.team;

import lombok.*;
import study.jpaProject.domain.BaseTimeEntity;
import study.jpaProject.domain.player.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Team extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy="team")
    private List<TeamRank> teamRanks = new ArrayList<>();

    @Column(nullable = false)
    private String teamName;

    @Column
    private String teamArea;

    @Column
    private String stadium;

    @Column
    private String manager;

    @Column
    private String foundingDate;

    @Column
    private String originalFileName;

    @Column
    private String saveFileName;

    @Builder
    public Team(String teamName, String teamArea, String stadium, String manager, String foundingDate, String originalFileName, String saveFileName) {
        this.teamName = teamName;
        this.teamArea = teamArea;
        this.stadium = stadium;
        this.manager = manager;
        this.foundingDate = foundingDate;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
    }

    public void updateTeam(String teamName, String teamArea, String stadium, String manager, String foundingDate, String originalFileName, String saveFileName){
        this.teamName = teamName;
        this.teamArea = teamArea;
        this.stadium = stadium;
        this.manager = manager;
        this.foundingDate = foundingDate;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
    }
}
