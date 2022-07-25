package study.jpaProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpaProject.repository.PlayerRankQueryRepository;
import study.jpaProject.repository.PlayerRankRepository;
import study.jpaProject.repository.PlayerRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlayerRankService {

    private final PlayerRankRepository playerRankRepository;
    private final PlayerRankQueryRepository playerRankQueryRepository;
    private final PlayerRepository playerRepository;



}
