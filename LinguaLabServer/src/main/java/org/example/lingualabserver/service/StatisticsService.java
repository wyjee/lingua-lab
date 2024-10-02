package org.example.lingualabserver.service;

import jakarta.transaction.Transactional;
import org.example.lingualabserver.domain.Statistics;
import org.example.lingualabserver.dto.AddStatisticsRequest;
import org.example.lingualabserver.dto.UpdateStatisticsRequest;
import org.example.lingualabserver.dto.DeleteStatisticsRequest;
import org.example.lingualabserver.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Statistics save(AddStatisticsRequest request) {
        return statisticsRepository.save(request.toEntity());
    }

    public List<Statistics> findAll() {
        // 페이지네이션 적용 필요
        return statisticsRepository.findAll();
    }

    public Statistics findById(long id) {
        return statisticsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public Statistics update(long id, UpdateStatisticsRequest request) {
    // isOverwrite 여부에 따라 필드 업데이트
        Statistics statistics = statisticsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        statistics.update(request.getIp(), request.getWordCount(), request.getCharacterCount(), request.getSentenceCount());

        return statistics;
    }

    public void deleteByIds(List<Long> ids) {
        statisticsRepository.deleteAllByIdInBatch(ids);
    }
}