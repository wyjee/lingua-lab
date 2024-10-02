package org.example.lingualabserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateHistoryRequest {
    private String fromLanguage;
    private String toLanguage;
    private String inputText;
    private String translatedText;
}