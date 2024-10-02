package org.example.lingualabserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.lingualabserver.domain.History;
import org.example.lingualabserver.dto.AddHistoryRequest;
import org.example.lingualabserver.dto.HistoryResponse;
import org.example.lingualabserver.dto.UpdateHistoryRequest;
import org.example.lingualabserver.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HistoryApiController {

    private final HistoryService historyService;

    @PostMapping("/history")
    public ResponseEntity<History> addHistory(@RequestBody AddHistoryRequest request) {
        History savedHistory = historyService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedHistory);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryResponse>> findAllHistory() {
        List<HistoryResponse> history = historyService.findAll()
                .stream()
                .map(HistoryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(history);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<HistoryResponse> findHistory(@PathVariable long id) {
        History history = historyService.findById(id);

        return ResponseEntity.ok()
                .body(new HistoryResponse(history));
    }

    @PutMapping("/history/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable long id,
                                                 @RequestBody UpdateHistoryRequest request) {
        History updatedHistory = historyService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedHistory);
    }
}