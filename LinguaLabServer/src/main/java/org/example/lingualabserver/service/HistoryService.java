package org.example.lingualabserver.service;

import jakarta.transaction.Transactional;
import org.example.lingualabserver.domain.History;
import org.example.lingualabserver.dto.AddHistoryRequest;
import org.example.lingualabserver.dto.UpdateHistoryRequest;
import org.example.lingualabserver.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public History save(AddHistoryRequest request) {
        return historyRepository.save(request.toEntity());
    }

    // 다른 CRUD 메소드 (조회, 수정, 삭제)

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public History findById(long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public History update(long id, UpdateHistoryRequest request) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        history.update(request.getFromLanguage(), request.getToLanguage(), request.getInputText(), request.getTranslatedText());

        return history;
    }
}