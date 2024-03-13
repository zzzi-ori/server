package com.game.zzio.service;

import com.game.zzio.domain.*;
import com.game.zzio.repository.RankRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RankService {

    private final RankRepository rankRepository;
    private final int pageSize = 20;

    public CreateRankResponse createRank(CreateRankRequest createRankRequest) {
        rankRepository.save(new UserRank(createRankRequest.getNickName(), createRankRequest.getScore()));

        List<UserRank> userRankList = rankRepository.findByNickName(createRankRequest.getNickName());
        long latestCreateDate = 0L;
        AtomicLong currentRankId = new AtomicLong();
        userRankList.forEach(userRank -> {
            if (userRank.getCreateDate() > latestCreateDate) {
                currentRankId.set(userRank.getId());
            }
        });

        UserRank userRank = rankRepository.findById(Long.parseLong(String.valueOf(currentRankId))).orElseThrow();
        List<UserRank> rankList = rankRepository.findRanksOrderByScore(userRank.getCreateDate());
        int count = (int) rankRepository.count();
        int myRank = 1;

        for (int i = 0; i < rankList.size(); i++) {
            UserRank rank = rankList.get(i);
            if (rank.getNickName().equals(createRankRequest.getNickName())) {
                myRank = i + 1;
                break;
            }
        }

        return CreateRankResponse.builder()
                .rank(myRank)
                .count(count)
                .currentTime(userRank.getCreateDate())
                .build();

    }

    public GetRankResult getRankList(int pageNumber, String dateTime) {
        List<UserRank> userRankList = rankRepository.findPreviousRanksOrderByScore(Long.valueOf(dateTime), PageRequest.of(pageNumber, pageSize));
        List<GetRankResponse> resultList = new ArrayList<>();

        if (userRankList.isEmpty()) {
            return GetRankResult.builder()
                    .rankList(resultList)
                    .totalCount(rankRepository.count())
                    .build();
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

        return GetRankResult.builder()
                .rankList(resultList)
                .totalCount(rankRepository.count())
                .nextPageNumber((long) (pageNumber + 1))
                .build();
    }

    public long getTotalRankCount() {
        return rankRepository.count();
    }

}
