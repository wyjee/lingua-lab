package org.example.lingualabserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.lingualabserver.domain.Statistics;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddStatisticsRequest {
    private String ip; // 사용자 ip 주소
    private Number wordCount; // 단어 수
    private Number characterCount; // 글자 수
    private Number sentenceCount; // 문장 수

    public Statistics toEntity() {
        return Statistics.builder()
                .ip(ip)
                .wordCount(wordCount)
                .characterCount(characterCount)
                .sentenceCount(sentenceCount)
                .build();
    }
}