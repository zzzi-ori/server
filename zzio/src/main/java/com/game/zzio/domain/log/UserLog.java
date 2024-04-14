package com.game.zzio.domain.log;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String logData;

    public UserLog(Long userId, String logData) {
        this.userId = userId;
        this.logData = logData;
    }
}
