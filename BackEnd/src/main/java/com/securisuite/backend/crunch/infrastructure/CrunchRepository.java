package com.securisuite.backend.crunch.infrastructure;

import com.securisuite.backend.crunch.domain.Crunch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrunchRepository extends JpaRepository<Crunch, Long> {
}
