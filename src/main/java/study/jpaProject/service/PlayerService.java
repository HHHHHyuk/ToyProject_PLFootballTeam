package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.domain.player.Player;
import study.jpaProject.domain.team.Team;
import study.jpaProject.error.CustomException;
import study.jpaProject.repository.PlayerQueryRepository;
import study.jpaProject.repository.PlayerRepository;
import study.jpaProject.repository.TeamRepository;
import study.jpaProject.utils.FileUtils;
import study.jpaProject.web.dto.player.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerQueryRepository playerQueryRepository;
    private final TeamRepository teamRepository;
    private final HttpServletRequest request;

    @Value("${spring.servlet.multipart.location}")
    private  String uploadPath;
    private final String imgPath = "images/";

    @Transactional
    public Long save(PlayerSaveRequestDto requestDto){
        if( requestDto.getPlayerName()==null || "".equals(requestDto.getPlayerName()) ) throw new CustomException("선수의 이름은 필수입력입니다.");
        Team team = teamRepository.findById(requestDto.getTeamId()).orElseThrow(() -> new IllegalArgumentException("소속되는 팀이 없습니다."));
        if(!playerRepository.findByTeamAndBackNumber(team, requestDto.getBackNumber()).isEmpty()) throw new CustomException("해당 등번호는 다른 선수의 등번호 입니다. 다른 등번호를 입력해주세요.");

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            FileUtils.move(request, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"player", requestDto.getSaveFileName());
        }
        return playerRepository.save(requestDto.toEntity(team)).getId();
    }

    @Transactional(readOnly = true)
    public Page<PlayerListResponseDto> findAllByPlayerNameAsc(PlayerSearchCondition condition, Pageable pageable){
        return playerQueryRepository.findByTeamId(condition, pageable);
    }

    public PlayerResponseDto findById(Long id){
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가지고있는 선수는 존재하지 않습니다. player_id =" + id));
        return new PlayerResponseDto(player);
    }

    @Transactional
    public Long update(Long id, PlayerUpdateRequestDto requestDto){
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가지고있는 선수는 존재하지 않습니다. player_id =" + id));
        Team team = teamRepository.findById(requestDto.getTeamId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가지고있는 팀이 존재하지 않습니다"));
        List<Player> backNumberPlayer = playerRepository.findByTeamAndBackNumber(team, requestDto.getBackNumber());
        if(backNumberPlayer!=null && backNumberPlayer.size()>0 && !backNumberPlayer.get(0).getId().equals(id)){
            throw new CustomException("해당 등번호는 다른 선수의 등번호 입니다. 다른 등번호를 입력해주세요.");
        }

        if(requestDto.getTempFileName()!=null && !"".equals(requestDto.getTempFileName())){
            if(player.getSaveFileName()!=null && !"".equals(player.getSaveFileName())){
                FileUtils.delete(request, imgPath+"player/"+player.getSaveFileName());
            }
            FileUtils.move(request, uploadPath+"/"+requestDto.getTempFileName(), imgPath+"player", requestDto.getSaveFileName());
        } else if ("Y".equals(requestDto.getDeleteYn())) {
            if (FileUtils.delete(request, imgPath + "player/" + player.getSaveFileName())) {
                requestDto.saveFileDelete();
            }
        }

        player.updatePlayer(team, requestDto.getPlayerName(), requestDto.getNationality(), requestDto.getHeight(), requestDto.getWeight(),
                requestDto.getPosition(), requestDto.getBackNumber(), requestDto.getOriginalFileName(), requestDto.getSaveFileName());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 선수는 존재하지 않습니다."));
        if(player.getSaveFileName()!=null && !"".equals(player.getSaveFileName())){
            FileUtils.delete(request, imgPath+"player/"+player.getSaveFileName());
        }
        playerRepository.delete(player);
    }

}
