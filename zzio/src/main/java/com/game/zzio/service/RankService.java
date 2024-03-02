package com.game.zzio.service;

import com.game.zzio.domain.*;
import com.game.zzio.repository.RankRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RankService {

    private final RankRepository rankRepository;
    private final int pageSize = 20;

    public void createRank(CreateRankRequest createRankRequest) {
        rankRepository.save(new UserRank(createRankRequest.getNickName(), createRankRequest.getScore()));
    }

    public List<GetRankResponse> getRankList(int pageNumber, String dateTime) {
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTime);
        List<UserRank> userRankList = rankRepository.findPreviousRanksOrderByScore(targetDateTime, PageRequest.of(pageNumber, pageSize));
        List<GetRankResponse> resultList = new ArrayList<>();

        if (userRankList.isEmpty()) {
            return resultList;
        }

        AtomicInteger rankNum = new AtomicInteger((pageNumber * pageSize) + 1);
        userRankList.forEach(userRank ->  {
            resultList.add(GetRankResponse.builder()
                    .score(userRank.getScore())
                    .nickName(userRank.getNickName())
                    .rank(Integer.parseInt(String.valueOf(rankNum)))
                    .build());
            rankNum.getAndIncrement();
        });

        return resultList;
    }

    public long getTotalRankCount() {
        return rankRepository.count();
    }

}
