package com.game.zzio.domain;

import lombok.Data;

@Data
public class GetRankRequest {
    private int pageNumber;
    private int pageSize;
}
