package com.game.zzio.domain.rank;

import lombok.Data;

@Data
public class CreateEventRequest {
    private Long userId;
    private String phoneNumber;
}
