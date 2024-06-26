package com.game.zzio.controller;

import com.game.zzio.domain.rank.CreateEventRequest;
import com.game.zzio.domain.rank.CreateRankRequest;
import com.game.zzio.domain.rank.CreateRankResponse;
import com.game.zzio.domain.rank.GetRankResult;
import com.game.zzio.service.RankService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RankController {

    private final RankService rankService;

    @PostMapping("")
    public CreateRankResponse saveRank(@RequestBody CreateRankRequest createRankRequest) {
        return rankService.createRank(createRankRequest);
    }

    @PostMapping("/event")
    public void saveEvent(@RequestBody CreateEventRequest createEventRequest) {
        rankService.createEvent(createEventRequest);
    }



    @GetMapping("")
    public GetRankResult getRank(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "dateTime") String dateTime
    ) {
        return rankService.getRankList(pageNumber, dateTime);
    }

    @GetMapping("/count")
    public Long getRankCount() {
        return rankService.getTotalRankCount();
    }
}
