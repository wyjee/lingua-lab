package org.example.lingualabserver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip; // 사용자 ip 주소
    private Number wordCount; // 단어 수
    private Number characterCount; // 글자 수
    private Number sentenceCount; // 문장 수

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Statistics(String ip, Number wordCount, Number characterCount, Number sentenceCount) {
        this.ip = ip;
        this.wordCount = wordCount;
        this.characterCount = characterCount;
        this.sentenceCount = sentenceCount;
    }

    public void update(String ip, Number wordCount, Number characterCount, Number sentenceCount) {
        this.ip = ip;
        this.wordCount = wordCount;
        this.characterCount = characterCount;
        this.sentenceCount = sentenceCount;
    }
}