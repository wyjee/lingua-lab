package org.example.lingualabserver.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromLanguage;

    @Column(nullable = false)
    private String toLanguage;

    @Column(nullable = false)
    private String inputText;

    @Column(nullable = false)
    private String translatedText;

    // User 엔티티와의 관계
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Builder
    public History(String fromLanguage, String toLanguage, String inputText, String translatedText) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.inputText = inputText;
        this.translatedText = translatedText;
    }

    public void update(String fromLanguage, String toLanguage, String inputText, String translatedText) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.inputText = inputText;
        this.translatedText = translatedText;
    }
}