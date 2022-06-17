package study.jpaProject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Player extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="player_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    @OneToMany(mappedBy="player")
    private List<PlayerRank> playerRank = new ArrayList<>();

    private String playerName;
    private String nationality;
    private Integer height;
    private Double weight;
    private String position;
    private Integer backNumber;
    private String originalFileName;
    private String saveFileName;

    public Player(Team team, String playerName, String nationality, Integer height, Double weight, String position, Integer backNumber, String originalFileName, String saveFileName) {
        this.playerName = playerName;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.backNumber = backNumber;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        if(team!=null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getPlayers().add(this);
    }

}
