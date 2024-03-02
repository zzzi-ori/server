package com.game.zzio.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRankResponse {
    private Integer myRank;
    private Integer rankCount;
    private Long currentTime;
}
