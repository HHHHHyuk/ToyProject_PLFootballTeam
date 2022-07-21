package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.jpaProject.service.PlayerService;
import study.jpaProject.utils.ApiUtils;
import study.jpaProject.utils.ApiUtils.ApiResult;
import study.jpaProject.web.dto.player.PlayerListResponseDto;
import study.jpaProject.web.dto.player.PlayerSaveRequestDto;
import study.jpaProject.web.dto.player.PlayerSearchCondition;
import study.jpaProject.web.dto.player.PlayerUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/api/v1/player")
    public ApiResult<Page<PlayerListResponseDto>> list(PlayerSearchCondition condition, Pageable pageable){
        Page<PlayerListResponseDto> list = playerService.findAllByPlayerNameAsc(condition, pageable);
        return ApiUtils.succes(list);
    }

    @PostMapping("/api/v1/player")
    public Long playerSave(@RequestBody PlayerSaveRequestDto requestDto){
        return playerService.save(requestDto);
    }

    @PutMapping("/api/v1/player/{id}")
    public Long playerUpdate(@PathVariable Long id, @RequestBody PlayerUpdateRequestDto requestDto) { return playerService.update(id, requestDto); }

    @DeleteMapping("/api/v1/player/{id}")
    public Long playerDelete(@PathVariable Long id) {
        playerService.delete(id);
        return id;
    }
}
