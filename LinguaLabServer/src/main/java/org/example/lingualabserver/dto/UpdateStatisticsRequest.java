package org.example.lingualabserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateStatisticsRequest {
    private String ip;
    private Number wordCount;
    private Number characterCount;
    private Number sentenceCount;
}