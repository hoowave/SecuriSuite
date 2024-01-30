package com.securisuite.backend.nmap.infrastructure;

import com.securisuite.backend.nmap.domain.Nmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NmapRepository extends JpaRepository<Nmap, Long> {
}
