package com.game.zzio.domain.rank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRankResponse {
    private Long userId;
    private Long rank;
    private Long count;
    private Long currentTime;
}
