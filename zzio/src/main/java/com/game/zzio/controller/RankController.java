package com.game.zzio.controller;

import com.game.zzio.domain.CreateRankRequest;
import com.game.zzio.domain.GetRankResponse;
import com.game.zzio.service.RankService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RankController {

    private final RankService rankService;

    @PostMapping("")
    public void saveRank(@RequestBody CreateRankRequest createRankRequest) {
        rankService.createRank(createRankRequest);
    }

    @GetMapping("")
    public List<GetRankResponse> getRank(
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
