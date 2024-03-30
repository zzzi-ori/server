package com.game.zzio.domain;

import lombok.Data;

@Data
public class CreateEventRequest {
    private Long userId;
    private String phoneNumber;
}
