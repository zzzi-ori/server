package com.game.zzio.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRankResponse {
    private String nickName;
    private Integer score;
    private Integer rank;
}
