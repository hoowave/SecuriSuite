package com.securisuite.backend.httrack.infrastructure;


import com.securisuite.backend.httrack.domain.wget.Wget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgetRepository extends JpaRepository<Wget, Long> {
}
