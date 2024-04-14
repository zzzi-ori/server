package com.game.zzio.domain.rank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRankResponse {
    private String nickName;
    private Long score;
    private Long rank;
    private Boolean eventYn;
}
