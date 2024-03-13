package com.game.zzio.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetRankResult {
    private List<GetRankResponse> rankList;
    private Long totalCount;
    private Long nextPageNumber;
}
