package com.game.zzio.domain.rank;

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
    private String phoneNumber;

    @Setter
    private Long score;

    @Column(updatable = false, columnDefinition = "BIGINT")
    private long createDate = System.currentTimeMillis() / 1000L;

    private String gameId;

    public UserRank(String nickName, Long score, String gameId) {
        this.nickName = nickName;
        this.score = score;
        this.gameId = gameId;
    }
}
