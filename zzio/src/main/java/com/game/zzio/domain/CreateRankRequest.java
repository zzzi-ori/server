package com.game.zzio.domain;

import lombok.Data;

@Data
public class CreateRankRequest {
    private String nickName;
    private Long score;
    private String gameId;
}
