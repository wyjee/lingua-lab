package org.example.lingualabserver.repository;

import org.example.lingualabserver.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}