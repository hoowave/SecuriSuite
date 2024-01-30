package com.securisuite.backend.system.infrastructure;

import com.securisuite.backend.system.domain.System;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<System, Long> {
}
