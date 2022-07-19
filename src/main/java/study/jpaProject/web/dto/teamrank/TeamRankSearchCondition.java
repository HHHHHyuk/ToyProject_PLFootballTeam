package study.jpaProject.web.dto.teamrank;

import lombok.Data;

@Data
public class TeamRankSearchCondition {
    private String season;
    private String orderValue;
    private boolean isAsc; //정렬방법 ASC, DESC
}
