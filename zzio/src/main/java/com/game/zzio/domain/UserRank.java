package com.game.zzio.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@RequiredArgsConstructor
public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Setter
    private Integer score;

    @Column(updatable = false, columnDefinition = "BIGINT")
    private long createDate = System.currentTimeMillis() / 1000L;

    public UserRank(String nickName, Integer score) {
        this.nickName = nickName;
        this.score = score;
    }
}
