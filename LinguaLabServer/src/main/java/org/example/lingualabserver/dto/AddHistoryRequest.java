package org.example.lingualabserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.lingualabserver.domain.History;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddHistoryRequest {
    private String fromLanguage;
    private String toLanguage;
    private String inputText;
    private String translatedText;

    public History toEntity() {
        return History.builder()
                .fromLanguage(fromLanguage)
                .toLanguage(toLanguage)
                .inputText(inputText)
                .translatedText(translatedText)
                .build();
    }
}