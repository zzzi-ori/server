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
        UserRank currentUserRank = rankRepository.save(new UserRank(createRankRequest.getNickName(), createRankRequest.getScore(), createRankRequest.getGameId()));

        Long currentRankId = currentUserRank.getId();

        UserRank userRank = rankRepository.findById(currentRankId).orElseThrow();
        List<UserRank> rankList = rankRepository.findRanksOrderByScore(userRank.getCreateDate());
        Long count = rankRepository.countByCreateDateTime(userRank.getCreateDate());
        Long myRank = (long) rankList.indexOf(currentUserRank)+1;

        return CreateRankResponse.builder()
                .userId(userRank.getId())
                .rank(myRank)
                .count(count)
                .currentTime(userRank.getCreateDate())
                .build();

    }

    public void createEvent(CreateEventRequest createEventRequest) {
        UserRank userRank = rankRepository.findById(createEventRequest.getUserId()).orElseThrow();
        userRank.setPhoneNumber(createEventRequest.getPhoneNumber());
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
                    .rank(Long.parseLong(String.valueOf(rankNum)))
                    .build());
            rankNum.getAndIncrement();
        });

        Long count = rankRepository.countByCreateDateTime(Long.valueOf(dateTime));

        return GetRankResult.builder()
                .rankList(resultList)
                .totalCount(count)
                .nextPageNumber((long) (pageNumber + 1))
                .build();
    }

    public long getTotalRankCount() {
        return rankRepository.count();
    }

}
