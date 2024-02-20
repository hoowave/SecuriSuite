package com.securisuite.backend.httrack.infrastructure;

import com.securisuite.backend.httrack.domain.Httrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HttrackRepository extends JpaRepository<Httrack, Long> {
    Httrack findByRegDts(String regDts);
}
