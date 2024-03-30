package com.game.zzio.repository;

import com.game.zzio.domain.UserRank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RankRepository extends JpaRepository<UserRank, Long> {

    @Query("SELECT r FROM UserRank r WHERE r.createDate <= ?1 ORDER BY r.score DESC, r.id ASC")
    List<UserRank> findRanksOrderByScore(Long dateTime);

    @Query("SELECT r FROM UserRank r WHERE r.createDate <= ?1 ORDER BY r.score DESC, r.id ASC")
    List<UserRank> findPreviousRanksOrderByScore(Long dateTime, Pageable pageable);

    @Query("SELECT COUNT(*) FROM UserRank r WHERE r.createDate <= ?1")
    Long countByCreateDateTime(Long dateTime);
}
