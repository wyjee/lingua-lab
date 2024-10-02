package org.example.lingualabserver.dto;

import lombok.Getter;
import org.example.lingualabserver.domain.History;

@Getter
public class HistoryResponse {

    private final String fromLanguage;
    private final String toLanguage;
    private final String inputText;
    private final String translatedText;

    public HistoryResponse(History history) {
        this.fromLanguage = history.getFromLanguage();
        this.toLanguage = history.getToLanguage();
        this.inputText = history.getInputText();
        this.translatedText = history.getTranslatedText();
    }
}