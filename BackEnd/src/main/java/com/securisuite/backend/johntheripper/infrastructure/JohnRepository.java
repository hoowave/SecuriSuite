package com.securisuite.backend.johntheripper.infrastructure;

import com.securisuite.backend.johntheripper.domain.John;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JohnRepository extends JpaRepository<John, Long> {
}
