package com.game.zzio.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRankResponse {
    private Integer rank;
    private Integer count;
    private Long currentTime;
}
