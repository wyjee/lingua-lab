package org.example.lingualabserver.repository;

import org.example.lingualabserver.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}