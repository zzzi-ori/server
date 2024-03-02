package com.game.zzio.repository;

import com.game.zzio.domain.UserRank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RankRepository extends JpaRepository<UserRank, Long> {
    @Query("SELECT r FROM UserRank r WHERE r.createDate <= ?1 ORDER BY r.score DESC")
    List<UserRank> findPreviousRanksOrderByScore(LocalDateTime dateTime, Pageable pageable);
}
