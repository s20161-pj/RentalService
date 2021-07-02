package com.rental.service.repository;

import com.rental.service.model.NbpResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbiApiResponseRepository extends JpaRepository<NbpResponse, Long> {
}
