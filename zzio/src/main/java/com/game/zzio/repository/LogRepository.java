package com.game.zzio.repository;

import com.game.zzio.domain.log.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<UserLog, Long> {
}
