package org.example.lingualabserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.lingualabserver.domain.Statistics;
import org.example.lingualabserver.dto.AddStatisticsRequest;
import org.example.lingualabserver.dto.DeleteStatisticsRequest;
import org.example.lingualabserver.dto.StatisticsResponse;
import org.example.lingualabserver.dto.UpdateStatisticsRequest;
import org.example.lingualabserver.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StatisticsApiController {

    private final StatisticsService statisticsService;

    @PostMapping("/stat")
    public ResponseEntity<Statistics> addHistory(@RequestBody AddStatisticsRequest request) {
        Statistics savedStatistics = statisticsService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedStatistics);
    }

    //  전체 조회
    @GetMapping("/stat")
    public ResponseEntity<List<StatisticsResponse>> findAllStatistics() {
        List<StatisticsResponse> statistics = statisticsService.findAll()
                .stream()
                .map(StatisticsResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(statistics);
    }

    @GetMapping("/stat/{id}")
    public ResponseEntity<StatisticsResponse> findStatistics(@PathVariable long id) {
        Statistics statistics = statisticsService.findById(id);

        return ResponseEntity.ok()
                .body(new StatisticsResponse(statistics));
    }

    @PutMapping("/stat/{id}")
    public ResponseEntity<Statistics> updateStatistics(@PathVariable long id,
                                                       @RequestBody UpdateStatisticsRequest request) {
        Statistics updatedStatistics = statisticsService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedStatistics);
    }

    @DeleteMapping("/stat")
    public ResponseEntity<Void> deleteStatistics(@RequestBody DeleteStatisticsRequest request) {
        statisticsService.deleteByIds(request.getIds());
        return ResponseEntity.noContent().build();
    }
}

