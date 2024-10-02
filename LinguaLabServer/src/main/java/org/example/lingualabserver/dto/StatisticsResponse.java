package org.example.lingualabserver.dto;

import lombok.Getter;
import org.example.lingualabserver.domain.Statistics;

@Getter
public class StatisticsResponse {

    private final Long id;
    private final String ip;
    private final Number wordCount;
    private final Number characterCount;
    private final Number sentenceCount;

    public StatisticsResponse(Statistics statistics) {
        this.id = statistics.getId();
        this.ip = statistics.getIp();
        this.wordCount = statistics.getWordCount();
        this.characterCount = statistics.getCharacterCount();
        this.sentenceCount = statistics.getSentenceCount();
    }
}